package com.hard.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hard.dao.PipelineExecutionStatusDatabaseDao;
import com.hard.enums.Status;
import com.hard.models.PipelineExecutionStatus;
import com.hard.models.TaskExecutionStatus;

@Repository("pipelineExecutionStatusDatabaseDao")
public class PipelineExecutionStatusDatabaseDaoImpl implements PipelineExecutionStatusDatabaseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PipelineExecutionStatus> getAll() {
		String sql = "SELECT * FROM pipeline_execution_status";
		
		List<PipelineExecutionStatus> pipelineExecutionStatuses = jdbcTemplate.query(
			sql,
			new RowMapper<PipelineExecutionStatus>() {
				@Override
				public PipelineExecutionStatus mapRow(ResultSet resultSet, int numRow) throws SQLException {
					PipelineExecutionStatus pipelineExecutionStatus = new PipelineExecutionStatus();
					
					pipelineExecutionStatus.setExecutionId(resultSet.getLong("id"));
					pipelineExecutionStatus.setPipeline(resultSet.getString("pipeline"));
					pipelineExecutionStatus.setStatus(Status.valueOf(resultSet.getString("status")));
					pipelineExecutionStatus.setStartTime(resultSet.getString("start_time"));
					
					long id = resultSet.getLong("id");
					
					String sql =
						"SELECT * FROM task_execution_status"
						+ " WHERE pipeline_execution_status_id = " + id;
					
					List<TaskExecutionStatus> taskExecutionStatuses = jdbcTemplate.query(
						sql,
						new RowMapper<TaskExecutionStatus>() {
							@Override
							public TaskExecutionStatus mapRow(ResultSet resultSet, int numRow) throws SQLException {
								TaskExecutionStatus taskExecutionStatus = new TaskExecutionStatus();
								
								taskExecutionStatus.setId(resultSet.getLong("id"));
								taskExecutionStatus.setName(resultSet.getString("name"));
								taskExecutionStatus.setStatus(Status.valueOf(resultSet.getString("status")));
								taskExecutionStatus.setStartTime(resultSet.getString("start_time"));
								taskExecutionStatus.setEndTime(resultSet.getString("end_time"));
								
								return taskExecutionStatus;
							}
						}
					);
					
					pipelineExecutionStatus.setTasks(taskExecutionStatuses);
					
					return pipelineExecutionStatus;
				}
			}
		);
		
		return pipelineExecutionStatuses;
	}
	
	@Override
	public PipelineExecutionStatus getById(long id) {
		String sql = "SELECT * FROM pipeline_execution_status WHERE id = ?";
		
		PipelineExecutionStatus pipelineExecutionStatus = jdbcTemplate.queryForObject(
			sql,
			new Object[] { id },
			new RowMapper<PipelineExecutionStatus>() {
				@Override
				public PipelineExecutionStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					PipelineExecutionStatus pipelineExecutionStatus = new PipelineExecutionStatus();
					
					pipelineExecutionStatus.setExecutionId(resultSet.getLong("id"));
					pipelineExecutionStatus.setPipeline(resultSet.getString("pipeline"));
					pipelineExecutionStatus.setStatus(Status.valueOf(resultSet.getString("status")));
					pipelineExecutionStatus.setStartTime(resultSet.getString("start_time"));
					
					long id = resultSet.getLong("id");
					
					String sql =
						"SELECT * FROM task_execution_status"
						+ " WHERE pipeline_execution_status_id = " + id;
					
					List<TaskExecutionStatus> taskExecutionStatuses = jdbcTemplate.query(
						sql,
						new RowMapper<TaskExecutionStatus>() {
							@Override
							public TaskExecutionStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {
								TaskExecutionStatus taskExecutionStatus = new TaskExecutionStatus();
								
								taskExecutionStatus.setId(resultSet.getLong("id"));
								taskExecutionStatus.setName(resultSet.getString("name"));
								taskExecutionStatus.setStatus(Status.valueOf(resultSet.getString("status")));
								taskExecutionStatus.setStartTime(resultSet.getString("start_time"));
								taskExecutionStatus.setEndTime(resultSet.getString("end_time"));
								
								return taskExecutionStatus;
							}
						}
					);
					
					pipelineExecutionStatus.setTasks(taskExecutionStatuses);
					
					return pipelineExecutionStatus;
				}
			}
		);
		
		return pipelineExecutionStatus;
	}
	
	@Override
	public void add(PipelineExecutionStatus model) {
		String sql_pipeline_execution_status =
			"INSERT INTO"
			+ " pipeline_execution_status (pipeline, status, start_time)"
			+ " VALUES (?, ?, ?)";
		
		jdbcTemplate.update(
			sql_pipeline_execution_status,
			new Object[] {
				model.getPipeline(),
				String.valueOf(model.getStatus()),
				model.getStartTime()
			}
		);
		
		String sql_lastId =
			"SELECT id FROM pipeline_execution_status"
			+ " WHERE id = (SELECT MAX(id) FROM pipeline_execution_status)";
		
		long lastId = jdbcTemplate.queryForObject(
			sql_lastId,
			new RowMapper<Long>() {
				@Override
				public Long mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					return resultSet.getLong("id");
				}
			}
		);
		
		String sql_task_execution_status =
			"INSERT INTO"
			+ " task_execution_status (name, status, start_time, end_time, pipeline_execution_status_id)"
			+ " VALUES (?, ?, ?, ?, ?)";
		
		for (TaskExecutionStatus taskExecutionStatus : model.getTasks()) {
			jdbcTemplate.update(
				sql_task_execution_status,
				taskExecutionStatus.getName(),
				String.valueOf(taskExecutionStatus.getStatus()),
				taskExecutionStatus.getStartTime(),
				taskExecutionStatus.getEndTime(),
				lastId
			);
		}
	}
	
	@Override
	public void update(PipelineExecutionStatus model) {
		String sql_pipeline_execution_status =
			"UPDATE pipeline_execution_status"
			+ " SET pipeline = ?, status = ?, start_time = ?"
			+ " WHERE id = ?";
		
		jdbcTemplate.update(
			sql_pipeline_execution_status,
			model.getPipeline(),
			String.valueOf(model.getStatus()),
			model.getStartTime(),
			model.getExecutionId()
		);
		
		String sql_execution_status =
			"UPDATE task_execution_status"
			+ " SET name = ?, status = ?, start_time = ?, end_time = ?"
			+ " WHERE id = ? AND pipeline_execution_status_id = ?";
		
		for (TaskExecutionStatus taskExecutionStatus : model.getTasks()) {
			jdbcTemplate.update(
				sql_execution_status,
				taskExecutionStatus.getName(),
				String.valueOf(taskExecutionStatus.getStatus()),
				taskExecutionStatus.getStartTime(),
				taskExecutionStatus.getEndTime(),
				taskExecutionStatus.getId(),
				model.getExecutionId()
			);
		}
	}
	
	@Override
	public void delete(PipelineExecutionStatus model) {
		String sql_task_execution_status =
			"DELETE FROM task_execution_status WHERE pipeline_execution_status_id = ?";
		
		jdbcTemplate.update(sql_task_execution_status, model.getExecutionId());
		
		String sql_pipeline_execution_status =
			"DELETE FROM pipeline_execution_status WHERE id = ?";
		
		jdbcTemplate.update(sql_pipeline_execution_status, model.getExecutionId());
	}
}