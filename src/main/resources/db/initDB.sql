DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
#DROP SEQUENCE IF EXISTS global_seq;

#CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
  id               INT AUTO_INCREMENT PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL,
  email            VARCHAR(255)            NOT NULL,
  password         VARCHAR(255)            NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  calories_per_day INTEGER DEFAULT 2000    NOT NULL
)
AUTO_INCREMENT = 100000;
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);