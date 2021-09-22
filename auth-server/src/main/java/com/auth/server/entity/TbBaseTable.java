package com.auth.server.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * The persistent class for the base_table database table.
 * 
 */

@Table(name = "public.tb_base_table")
@MappedSuperclass
public class TbBaseTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "description")
	private String description;

	@Column(name = "created")
	private Instant createTime;

	@Column(name = "updated")
	private Instant modifyTime;

	protected TbBaseTable() {
		// Do nothing
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	public Instant getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Instant modifyTime) {
		this.modifyTime = modifyTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}