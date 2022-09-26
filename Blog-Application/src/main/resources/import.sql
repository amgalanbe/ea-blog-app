INSERT INTO users (id, is_active, email, firstname, lastname, `password`, username) VALUES (1, true, 'admin@miu.edu', 'Admin', 'Admin', '$2a$10$qm5X9EQgr53QiFIEneWdw..ozeXgT72YGobjd5kDwl.COnxHD6QvW', 'admin'); #admin
INSERT INTO users (id, is_active, email, firstname, lastname, `password`, username) VALUES (2, true, 'amgalan@miu.edu', 'Amgalan', 'Bat-Erdene', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'amga'); #123
INSERT INTO users (id, is_active, email, firstname, lastname, `password`, username) VALUES (3, true, 'harriet@miu.edu', 'Harriet', 'Nakayenga', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'harriet'); #123
INSERT INTO users (id, is_active, email, firstname, lastname, `password`, username) VALUES (4, true, 'dennis@miu.edu', 'Dennis', 'Nyoni', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'dennis'); #123

INSERT INTO role (`role`) VALUES ('ADMIN');
INSERT INTO role (`role`) VALUES ('USER');

INSERT INTO users_roles (user_id, roles_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES (3, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES (4, 1);

INSERT INTO posts (id, body, creation_date, title, user_id) VALUES (1, 'What happens when a dog gets bitten by a black widow spider, and what can you do about it?', '2021-11-27 12:52:01', 'Black Widow Bites in Dogs', 3);
INSERT INTO posts (id, body, creation_date, title, user_id) VALUES (2, 'In this article we’ll discuss the questions you should ask when adopting a puppy or dog, and help you make the best decision on your future furry friend!', '2021-11-28 11:52:01', 'Quick Health Tips & Questions for Dog and Puppy Adoption', 4);
INSERT INTO posts (id, body, creation_date, title, user_id) VALUES (3, 'Has your dog recently been diagnosed with a bacterial infection? Quickly noticing that there is something wrong with your dog will help you be able to fix these issues and get your dog back to its normal healthy, and happy life.', '2021-11-29 5:15:01', 'Bacterial Infections in Dogs - Different Types and Treatment', 2);
INSERT INTO posts (id, body, creation_date, title, user_id) VALUES (4, 'Do Service Dogs Have To Be Registered?', '2021-12-27 11:35:01', 'Service dogs are professional pups that offer a world of help to those who need it. In this article, we’ll discuss the details of service dog work, and help you better understand how to register a service dog going forward.', 2);
INSERT INTO posts (id, body, creation_date, title, user_id) VALUES (5, 'What is the Best Dog Training Collar', '2022-1-27 3:8:01', 'From the moment you welcome your canine companion into your home, you will want to begin their training process. Proper training is essential in creating a well rounded pup, and one that you will be proud to have at your side. While most of their training involves our diligent participation and positive reinforcement, there are a few training tools that make this process easier.', 3);


