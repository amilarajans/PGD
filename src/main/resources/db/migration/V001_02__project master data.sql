CREATE TABLE `mst_department` (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT,
  `d_code`        VARCHAR(75)  NULL,
  `d_name`        VARCHAR(150) NULL,
  `d_description` VARCHAR(255) NULL,
  `created_by`    VARCHAR(50)  NULL,
  `created_date`  DATETIME     NULL,
  `modified_by`   VARCHAR(50)  NULL,
  `modified_date` DATETIME     NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_depart_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_info` (`login`),
  CONSTRAINT `fk_depart_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user_info` (`login`)
);

CREATE TABLE `mst_course` (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT,
  `fk_department` BIGINT       NULL,
  `c_code`        VARCHAR(75)  NULL,
  `c_name`        VARCHAR(150) NULL,
  `c_year`        VARCHAR(10)  NULL,
  `c_from_date`   DATETIME     NULL,
  `c_to_date`     DATETIME     NULL,
  `c_description` VARCHAR(255) NULL,
  `created_by`    VARCHAR(50)  NULL,
  `created_date`  DATETIME     NULL,
  `modified_by`   VARCHAR(50)  NULL,
  `modified_date` DATETIME     NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_course_department` FOREIGN KEY (`fk_department`) REFERENCES `mst_department` (`id`),
  CONSTRAINT `fk_course_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_info` (`login`),
  CONSTRAINT `fk_course_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user_info` (`login`)
);

CREATE TABLE `mst_course_payments` (
  `id`             BIGINT       NOT NULL AUTO_INCREMENT,
  `fk_department`  BIGINT       NULL,
  `fk_course`      BIGINT       NULL,
  `cp_amount`      VARCHAR(150) NULL,
  `cp_description` VARCHAR(255) NULL,
  `created_by`     VARCHAR(50)  NULL,
  `created_date`   DATETIME     NULL,
  `modified_by`    VARCHAR(50)  NULL,
  `modified_date`  DATETIME     NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cp_department` FOREIGN KEY (`fk_department`) REFERENCES `mst_department` (`id`),
  CONSTRAINT `fk_cp_course` FOREIGN KEY (`fk_course`) REFERENCES `mst_course` (`id`),
  CONSTRAINT `fk_cp_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_info` (`login`),
  CONSTRAINT `fk_cp_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user_info` (`login`)
);


CREATE TABLE `mst_exam` (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT,
  `fk_department` BIGINT       NULL,
  `fk_course`     BIGINT       NULL,
  `e_code`        VARCHAR(75)  NULL,
  `e_name`        VARCHAR(150) NULL,
  `e_description` VARCHAR(255) NULL,
  `created_by`    VARCHAR(50)  NULL,
  `created_date`  DATETIME     NULL,
  `modified_by`   VARCHAR(50)  NULL,
  `modified_date` DATETIME     NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_exam_department` FOREIGN KEY (`fk_department`) REFERENCES `mst_department` (`id`),
  CONSTRAINT `fk_exam_course` FOREIGN KEY (`fk_course`) REFERENCES `mst_course` (`id`),
  CONSTRAINT `fk_exam_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_info` (`login`),
  CONSTRAINT `fk_exam_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user_info` (`login`)
);


CREATE TABLE `mst_student` (
  `id`               BIGINT       NOT NULL AUTO_INCREMENT,
  `s_id`             VARCHAR(75)  NULL,
  `s_title`          VARCHAR(50)  NULL,
  `s_full_name`      VARCHAR(255) NULL,
  `s_p_address`      VARCHAR(255) NULL,
  `s_c_address`      VARCHAR(255) NULL,
  `s_phone`          VARCHAR(20)  NULL,
  `s_fax`            VARCHAR(20)  NULL,
  `s_email`          VARCHAR(150) NULL,
  `s_dob`            DATETIME     NULL,
  `s_gender`         DATETIME     NULL,
  `s_citizenship`    VARCHAR(150) NULL,
  `s_nic`            VARCHAR(20)  NULL,
  `s_designation`    VARCHAR(150) NULL,
  `s_duties`         VARCHAR(255) NULL,
  `s_office_address` VARCHAR(255) NULL,
  `s_office_phone`   VARCHAR(20)  NULL,
  `s_office_fax`     VARCHAR(20)  NULL,
  `s_office_email`   VARCHAR(150) NULL,
  `created_by`       VARCHAR(50)  NULL,
  `created_date`     DATETIME     NULL,
  `modified_by`      VARCHAR(50)  NULL,
  `modified_date`    DATETIME     NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_student_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_info` (`login`),
  CONSTRAINT `fk_student_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user_info` (`login`)
);

CREATE TABLE `trn_exam_result` (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT,
  `fk_department` BIGINT       NULL,
  `fk_course`     BIGINT       NULL,
  `fk_exam`       BIGINT       NULL,
  `e_result`      VARCHAR(75)  NULL,
  `e_description` VARCHAR(255) NULL,
  `created_by`    VARCHAR(50)  NULL,
  `created_date`  DATETIME     NULL,
  `modified_by`   VARCHAR(50)  NULL,
  `modified_date` DATETIME     NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_er_department` FOREIGN KEY (`fk_department`) REFERENCES `mst_department` (`id`),
  CONSTRAINT `fk_er_course` FOREIGN KEY (`fk_course`) REFERENCES `mst_course` (`id`),
  CONSTRAINT `fk_er_exam` FOREIGN KEY (`fk_course`) REFERENCES `mst_exam` (`id`),
  CONSTRAINT `fk_er_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_info` (`login`),
  CONSTRAINT `fk_er_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user_info` (`login`)
);