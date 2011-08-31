
###############################################################################
# Database Scheme for SBBS 3.0.0
# Mar 8, 2006
###############################################################################

###############################################################################
# Description:
#
CREATE TABLE sbbs_boards (
	board_id			int auto_increment,

	board_name			varchar(32),
	board_description	varchar(255),
	board_template_name	varchar(32),
	board_post_count		int,

	board_attributes		int,

	PRIMARY KEY(board_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_board_extended (
	boardx_id			int auto_increment,
	board_id			int,
	boardx_key			varchar(32),
	boardx_value		varchar(255),

	boardx_attributes		int,

	PRIMARY KEY(boardx_id),
	INDEX ref (board_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_board_categories (
	category_id				int auto_increment,
	board_id				int,
	category_name			varchar(255),
	category_description	varchar(255),

	category_attributes		int,

	PRIMARY KEY(category_id),
	INDEX ref (board_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_board_accesslist (
	id				int auto_increment,
	board_id			int,

	target			char(8),
	target_id			int,
	target_section	char(8),
	order				int,
	permission		int,

	attributes		int,

	PRIMARY KEY(id),
	INDEX ref (board_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_board_cache (
	id			int auto_increment,
	board_id		int,
#...(¹Ì¿Ï¼º)

	attributes		int,

	PRIMARY KEY(id),
	INDEX ref (board_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_$boardname_posts (
	post_id				int auto_increment,
	board_id			int,

#(for arrangement)
	post_parent_post_id	int,
	post_depth			int,
	post_child_count	int,

	post_created_date	datetime,
	post_modified_date		datetime,
	post_deleted_date		datetime,
	post_slug			varchar(255),


	post_title				varchar(255),
	post_content			text,

	post_attributes		int,

	PRIMARY KEY(post_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_$boardname_post_extended (
	postx_id		int auto_increment,
	post_id			int,
	postx_key		varchar(32),
	postx_value		varchar(255),

	postx_attributes	int,

	PRIMARY KEY(postx_id),
	INDEX ref (post_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_$boardname_post_categoryinfo (
	id			int auto_increment,
	post_id		int,
	category_id	int,

	attributes		int,

	PRIMARY KEY(id),
	INDEX ref (post_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_$boardname_post_accesslist (
	id			int auto_increment,
	post_id		int,

	field_name	varchar(32),
	target		varchar(32),
	target_id		int,
	permission	int,

	attributes		int,

	PRIMARY KEY(id),
	INDEX ref (post_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_users (
	user_id				int auto_increment,

	user_name			varchar(32),
	user_password		varchar(64),

	user_email			varchar(255),

	user_family_name	varchar(32),
	user_given_name		varchar(32),
	user_middle_name	varchar(32),
	user_nick_name		varchar(32),

	user_nation			varchar(32),
	user_state			varchar(32),
	user_city			varchar(32),
	user_address		varchar(64),
	user_zip			varchar(16),

	user_timezone		int,
	user_signature		varchar(255),

	user_group_count	int,
	user_created_date	datetime,
	user_modified_date		datetime,
	user_deleted_date		datetime,

	user_attributes		int,

	PRIMARY KEY(user_id),
	INDEX index_user (user_name)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_user_extended (
	userx_id			int auto_increment,
	user_id				int,
	userx_key			varchar(32),
	userx_value			varchar(255),

	userx_attributes		int,

	PRIMARY KEY(userx_id),
	INDEX ref (user_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_user_accesslist (
	id			int auto_increment,
	user_id		int,
	
	field_name	varchar(32),
	target		varchar(32),
	target_id		int,
	permission	int,

	attributes		int,

	PRIMARY KEY(id),
	INDEX ref (user_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_user_groupinfo (
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_groups (
	group_id				int auto_increment,

	group_created_date		datetime,
	group_modified_date		datetime,
	group_deleted_date		datetime,

	group_parent_id			int,
	group_depth				int,
	group_child_count		int,

	group_name				varchar(32),
	group_description		varchar(255),

	group_attributes			int,

	PRIMARY KEY(group_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_group_extended (
	groupx_id			int auto_increment,
	group_id			int,
	groupx_key			varchar(32),
	groupx_value		varchar(255),
	groupx_attributes		int,

	PRIMARY KEY(groupx_id),
	INDEX ref (group_id)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_config (
	config_key			varchar(32),
	config_value		varchar(255),

	PRIMARY KEY(config_key)
);


###############################################################################
# Description:
#
CREATE TABLE sbbs_logs (
	log_id			int auto_increment,
	log_date		datetime,
	log_content		varchar(255),

	PRIMARY KEY(log_id)
);