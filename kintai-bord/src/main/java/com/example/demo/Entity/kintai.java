package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

	@Entity
	@Data
	@Table(name = "kintai")
	public class kintai {

		/**
		* 日付
		*/
		@Id
		@Column(name = "ymd")
		private String ymd;

		/**
		 * 開始時間
		 */
		@Column(name = "work_st")
		private String work_st;

		/**
		 * 終了時間
		 */
		@Column(name = "work_ed")
		private String work_ed;

		/**
		 * 休憩時間
		 */
		@Column(name = "work_rt")
		private String work_rt;
	}

