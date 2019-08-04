

INSERT INTO OFFER (ID, AVAILABLE_FOR_COLLECTION, OFFER_ENDING_DATE, IS_ACCEPTED) VALUES (1, '2019-07-20 10:00:00', '2019-07-24 10:00:00', false);
INSERT INTO OFFER (ID, AVAILABLE_FOR_COLLECTION, OFFER_ENDING_DATE, IS_ACCEPTED) VALUES (2, '2019-07-21 10:00:00', '2019-07-25 10:00:00', true);
INSERT INTO OFFER (ID, AVAILABLE_FOR_COLLECTION, OFFER_ENDING_DATE, IS_ACCEPTED) VALUES (3, '2019-07-22 10:00:00', '2019-07-26 10:00:00', false);

INSERT INTO OFFER_PRODUCT_BATCH_MAPPING (OFFER_ID, PRODUCT_BATCH_ID) VALUES (1,1);
INSERT INTO OFFER_PRODUCT_BATCH_MAPPING (OFFER_ID, PRODUCT_BATCH_ID) VALUES (1,2);

INSERT INTO OFFER_PRODUCT_BATCH_MAPPING (OFFER_ID, PRODUCT_BATCH_ID) VALUES (2,3);
INSERT INTO OFFER_PRODUCT_BATCH_MAPPING (OFFER_ID, PRODUCT_BATCH_ID) VALUES (3,4);