INSERT INTO user (enabled, login, password, email)
    VALUE (1, 'admin', '$2a$10$JV2Okj1B4VDDQIvZkfKolOOrgfloGMpr4GuwF5Ha61MoSmzykWj82', 'email@email.com'),
    (1, 'guest', '$2a$10$ni8T1vjbq0MIQddtnF6Zmuwg6vN6MGaNPgWNoj0kw5XnNHsmZQbSa', 'email@email.com');
GO

INSERT INTO authority (authority)
    VALUE ('ROLE_ADMIN'), ('ROLE_GUEST');
GO

INSERT INTO user_authority (user_id, authority_id)
SELECT (SELECT id FROM `user` WHERE `login` = 'admin'), (SELECT id FROM `authority` WHERE `authority` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `user` WHERE `login` = 'guest'), (SELECT id FROM `authority` WHERE `authority` = 'ROLE_GUEST');
GO