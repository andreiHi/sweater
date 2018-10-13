DELETE FROM user_role;
DELETE FROM usr;

INSERT INTO usr(id,  active,  password, username) VALUES
  (1, true,'$2a$08$sshqrbuTvYszPAP5UClrXOxuwPbBkTfDCnXrBjDkyFa8Z8HK80XK.', 'andr'),
  (2, true,'$2a$08$sshqrbuTvYszPAP5UClrXOxuwPbBkTfDCnXrBjDkyFa8Z8HK80XK.', 'mike');

INSERT INTO user_role(user_id, roles) VALUES
  (1, 'ADMIN'),(1, 'USER'),
  (2, 'USER');