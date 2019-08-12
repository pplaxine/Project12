

INSERT INTO GIVE_AWAY (ID, AVAILABLE_TO_BE_COLLECTED_FROM, COLLECTION_DATE, ORGANISATION_ID) VALUES (1, '2019-07-15 10:00:00', '2019-07-15 11:00:00', 1);
INSERT INTO GIVE_AWAY (ID, AVAILABLE_TO_BE_COLLECTED_FROM, COLLECTION_DATE, ORGANISATION_ID) VALUES (2, '2019-07-17 10:00:00', '2019-07-17 11:00:00', 1);
INSERT INTO GIVE_AWAY (ID, AVAILABLE_TO_BE_COLLECTED_FROM, ORGANISATION_ID) VALUES (3, '2019-07-21 14:00:00', 1);

INSERT INTO CONTAINER (ID, ACCEPTED, COLLECTION_RUN_ID, DESCRIPTION, IS_COLLECTED, GIVEAWAY_ID) VALUES (1, true, 1, 'carrots', false,1);
INSERT INTO CONTAINER (ID, ACCEPTED, COLLECTION_RUN_ID, DESCRIPTION, IS_COLLECTED, GIVEAWAY_ID) VALUES (2, true, 1, 'potatoes', false,1);
INSERT INTO CONTAINER (ID, ACCEPTED, DESCRIPTION, IS_COLLECTED, GIVEAWAY_ID) VALUES (3, false, 'vodka', false,2);
INSERT INTO CONTAINER (ID, ACCEPTED, DESCRIPTION, IS_COLLECTED, GIVEAWAY_ID) VALUES (4, true, 'Milk', false,3);
