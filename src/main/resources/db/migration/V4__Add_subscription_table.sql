CREATE TABLE  user_subscriptions(
  chanel_id int8 NOT NULL REFERENCES usr,
  subscriber_id int8 NOT NULL REFERENCES usr,
  PRIMARY KEY (chanel_id, subscriber_id)
)