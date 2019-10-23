DROP TABLE IF EXISTS user_roles, users, meals;

CREATE TABLE users
(
  id               INTEGER AUTO_INCREMENT PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL,
  email            VARCHAR(255)            NOT NULL,
  password         VARCHAR(255)            NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL      DEFAULT TRUE  NOT NULL,
  calories_per_day INTEGER   DEFAULT 2000  NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 100000;

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE meals
(
  id          INTEGER AUTO_INCREMENT PRIMARY KEY,
  dateTime    DATETIME     NOT NULL,
  description VARCHAR(255) NOT NULL,
  calories    INTEGER      NOT NULL,
  user_id     INTEGER      NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE UNIQUE INDEX meals_unique_user_datetime_idx
  ON meals (user_id, datetime);