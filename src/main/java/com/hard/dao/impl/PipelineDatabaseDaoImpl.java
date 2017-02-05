package com.hard.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hard.dao.PipelineDatabaseDao;
import com.hard.enums.Type;
import com.hard.models.Action;
import com.hard.models.Pipeline;
import com.hard.models.Task;

@Repository("pipelineDatabaseDao")
public class PipelineDatabaseDaoImpl implements PipelineDatabaseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Pipeline> getAll() {	// add 3 level
		String sql = "SELECT * FROM pipeline";
		
		List<Pipeline> pipelines = jdbcTemplate.query(
			sql,
			new RowMapper<Pipeline>() {
				@Override
				public Pipeline mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					Pipeline pipeline = new Pipeline();
					
					pipeline.setId(resultSet.getInt("id"));
					pipeline.setName(resultSet.getString("name"));
					pipeline.setDescription(resultSet.getString("description"));
					
					long id = resultSet.getLong("id");
					
					String sql =
						"SELECT * FROM task"
						+ " WHERE pipeline_id = " + id;
					
					List<Task> tasks = jdbcTemplate.query(
						sql,
						new RowMapper<Task>() {
							@Override
							public Task mapRow(ResultSet resultSet, int numRow) throws SQLException {
								Task task = new Task();
								
								task.setId(resultSet.getLong("id"));
								task.setName(resultSet.getString("name"));
								task.setDescription(resultSet.getString("description"));
								
								return task;
							}
						}
					);
					
					pipeline.setTasks(tasks);
					
					return pipeline;
				}
			}
		);
		
		return pipelines;
	}
	
	@Override
	public Pipeline getById(long id) {
		String sql = "SELECT * FROM pipeline WHERE id = ?";
		
		Pipeline pipeline = jdbcTemplate.queryForObject(
			sql,
			new Object[] { id },
			new RowMapper<Pipeline>() {
				@Override
				public Pipeline mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					Pipeline pipeline = new Pipeline();
					
					pipeline.setId(resultSet.getInt("id"));
					pipeline.setName(resultSet.getString("name"));
					pipeline.setDescription(resultSet.getString("description"));
					
					long id = resultSet.getLong("id");
					
					String sql =
						"SELECT * FROM task"
						+ " WHERE pipeline_id = " + id;
					
					List<Task> tasks = jdbcTemplate.query(
						sql,
						new RowMapper<Task>() {
							@Override
							public Task mapRow(ResultSet resultSet, int numRow) throws SQLException {
								Task task = new Task();
								
								task.setId(resultSet.getLong("id"));
								task.setName(resultSet.getString("name"));
								task.setDescription(resultSet.getString("description"));
								
								long id = resultSet.getLong("id");// 3 level /
								
								String sql =
									"SELECT * FROM action"
									+ " WHERE task_id = " + id;
								
								Action action = jdbcTemplate.queryForObject(
									sql,
									new RowMapper<Action>() {
										@Override
										public Action mapRow(ResultSet resultSet, int numRow) throws SQLException {
											Action action = new Action();
											
											action.setId(resultSet.getLong("id"));
											action.setType(Type.valueOf(resultSet.getString("type")));
											
											return action;
										}
									}
								);
								
								task.setAction(action);	// / 3 level
								
								return task;
							}
						}
					);
					
					pipeline.setTasks(tasks);
					
					return pipeline;
				}
			}
		);
		
		return pipeline;
	}
	
	@Override
	public void add(Pipeline model) {
		String sql_pipeline = "INSERT INTO pipeline (name, description) VALUES (?, ?)";
		
		jdbcTemplate.update(
			sql_pipeline,
			model.getName(),
			model.getDescription()
		);
		
		String sql_lastPipelineId =
			"SELECT id FROM pipeline"
			+ " WHERE id = (SELECT MAX(id) FROM pipeline)";
		
		long lastPiplineId = jdbcTemplate.queryForObject(
			sql_lastPipelineId,
			new RowMapper<Long>() {
				@Override
				public Long mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					return resultSet.getLong("id");
				}
			}
		);
		
		String sql_task =
			"INSERT INTO"
			+ " task (name, description, pipeline_id)"
			+ " VALUES (?, ?, ?)";
		
		// 3 level/
		String sql_action =
			"INSERT INTO action (type, task_id)"
			+ " VALUES (?, ?)";
		
		String sql_lastTaskId = "SELECT id FROM task"
			+ " WHERE id = (SELECT MAX(id) FROM task)";
		// /3 level
		
		for (Task task : model.getTasks()) {
			jdbcTemplate.update(
				sql_task,
				task.getName(),
				task.getDescription(),
				lastPiplineId
			);
			
			// 3 level/
			long lastTaskId = jdbcTemplate.queryForObject(
				sql_lastTaskId,
				new RowMapper<Long>() {
					@Override
					public Long mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return resultSet.getLong("id");
					}
				}
			);	// /3 level
			
			jdbcTemplate.update(
				sql_action,
				String.valueOf(task.getAction().getType()),
				lastTaskId
			);
		}
	}
	
	@Override
	public void update(Pipeline model) {	// add 3 level
		String sql_pipeline =
			"UPDATE pipeline"
			+ " SET name = ?, description = ?"
			+ " WHERE id = ?";
		
		jdbcTemplate.update(
			sql_pipeline,
			model.getName(),
			model.getDescription(),
			model.getId()
		);
		
		String sql_task =
			"UPDATE task"
			+ " SET name = ?, description = ?"
			+ " WHERE id = ? AND pipeline_id = ?";
		
		for (Task task : model.getTasks()) {
			jdbcTemplate.update(
				sql_task,
				task.getName(),
				task.getDescription(),
				task.getId(),
				model.getId()
			);
		}
	}
	
	@Override
	public void delete(Pipeline model) {
		String sql_action = "DELETE FROM action WHERE task_id = ?";	// 3 level
		
		for (Task task : model.getTasks())
			jdbcTemplate.update(sql_action, task.getId());
		
		String sql_task = "DELETE FROM task WHERE pipeline_id = ?";	// 2 level
		
		jdbcTemplate.update(sql_task, model.getId());
		
		String sql_pipeline = "DELETE FROM pipeline WHERE id = ?";	// 1 level
		
		jdbcTemplate.update(sql_pipeline, model.getId());
	}
}