/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 01/09/2018 10:19:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(11) NOT NULL,
  `book_price` decimal(10, 2) DEFAULT NULL,
  `book_storage` int(11) DEFAULT NULL,
  `book_volume` int(11) DEFAULT NULL,
  `book_score` double(10, 2) DEFAULT 1.00,
  `book_delete` tinyint(4) DEFAULT 0 COMMENT '0为在库，1为已删除',
  PRIMARY KEY (`book_id`) USING BTREE,
  CONSTRAINT `fk_book_bookinfo` FOREIGN KEY (`book_id`) REFERENCES `book_info` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1001, 37.40, 23, 104, 4.50, 0);
INSERT INTO `book` VALUES (1002, 102.40, 14, 535, 4.90, 0);
INSERT INTO `book` VALUES (1003, 43.50, 25, 326, 4.80, 1);
INSERT INTO `book` VALUES (1004, 28.70, 14, 75, 4.90, 0);
INSERT INTO `book` VALUES (1005, 21.60, 23, 53, 4.70, 0);
INSERT INTO `book` VALUES (1006, 73.30, 9, 75, 4.80, 0);
INSERT INTO `book` VALUES (1007, 36.40, 34, 63, 4.60, 0);
INSERT INTO `book` VALUES (1008, 4.00, 6, 34, 4.90, 0);
INSERT INTO `book` VALUES (1009, 15.00, 34, 53, 4.80, 0);
INSERT INTO `book` VALUES (1010, 41.40, 56, 75, 4.80, 0);
INSERT INTO `book` VALUES (1011, 28.10, 34, 64, 4.90, 0);
INSERT INTO `book` VALUES (1012, 53.50, 19, 34, 4.60, 0);
INSERT INTO `book` VALUES (1013, 35.80, 3, 23, 4.90, 0);
INSERT INTO `book` VALUES (1014, 45.80, 4, 54, 4.60, 0);
INSERT INTO `book` VALUES (1015, 34.70, 2, 37, 4.70, 1);
INSERT INTO `book` VALUES (1016, 45.50, 8, 26, 4.80, 1);
INSERT INTO `book` VALUES (1018, 56.00, 10, 35, 4.80, 0);
INSERT INTO `book` VALUES (1019, 45.00, 16, 93, 4.70, 1);
INSERT INTO `book` VALUES (1033, 34.00, 64, 0, 0.00, 0);
INSERT INTO `book` VALUES (1035, 34.00, 45, 0, 0.00, 1);
INSERT INTO `book` VALUES (1044, 26.50, 65, 0, 0.00, 0);

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info`  (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_isbn` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_author` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `book_publish` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `book_kind` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `book_summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `book_publtime` date DEFAULT NULL,
  `book_cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1043 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES (1001, '9787540487676', '总要习惯一个人', '蕊希 ', '湖南文艺出版社', '文学', '治愈千万人、音频总播放量过30亿的人气电台主持人、作家蕊希诚意新作。所有的煎熬和孤独，都成了我走向你的路。世界欠我一个你，是世界欠的，不是你。写给每一个爱过哭过失去过，但依然在用力成长的你。', '2016-06-11', 'img/1001.jpg');
INSERT INTO `book_info` VALUES (1002, '9787111407010', '算法导论（原书第3版）', '(美)科尔曼', '机械工业出版社', '工学', '本书将严谨性和全面性融为一体，深入讨论各类算法，并着力使这些算法的设计和分析能为各个层次的读者接受。全书各章自成体系，可以作为独立的学习单元；算法以英语和伪代码的形式描述，具备初步程序设计经验的人就能看懂；说明和解释力求浅显易懂，不失深度和数学严谨性。\r\n　　全书选材经典、内容丰富、结构合理、逻辑清晰，对本科生的数据结构课程和研究生的算法课程都是非常实用的教材，在IT专业人员的职业生涯中，本书也是一本案头必备的参考书或工程实践手册。', '2013-01-01', 'img/1002.jpg');
INSERT INTO `book_info` VALUES (1003, '9787214067593', '几何原本', '(古希腊)欧几里得', '江苏人民出版社', '自然科学', '《几何原本》是古希腊数学家欧几里得的一部不朽之作，集整个古希腊数学的成果与精神于一身。既是数学巨著，也是哲学巨著，并且第一次完成了人类对空间的认识。该书自问世之日起，在长达两千多年的时间里，历经多次翻译和修订，自1482年第一个印刷本出版，至今已有一千多种不同版本。除《圣经》之外，没有任何其他著作，其研究、使用和传播之广泛能够与《几何原本》相比。', '2017-11-12', 'img/1003.jpg');
INSERT INTO `book_info` VALUES (1004, '9787548059974', '道德经', '(春秋)老子', '江西美术出版社', '文学', '全书分为上、下两篇。上篇称《道经》，下篇为《德经》。《道经》讲述的是宇宙根本，道出了天地万物变化的玄机，揭示了阴阳变幻的微妙。《德经》讲述的是处世方略。道出了人事的进退之术。本书在忠于原著的基础上，详细注解并翻译原文，并针对每章内容分别从为人之道、从政之道、经商之道等三大方向列举了大量妙趣横生的古今中外案例，力求呈现出多方位的立体化效果，使读者能够更好体会和感悟两千多年前的哲人圣典。', '2018-06-01', 'img/1004.jpg');
INSERT INTO `book_info` VALUES (1005, '9787544419321', '哈农钢琴练指法', '江晨', '上海教育出版社', '艺术', '这部作品注重左右手的全面均衡的技术训练，对手指的速度、力度以及独立性的训练效果非常显著，第1－37首练习便是专为此而创作的。在之后的练习中，包括了24个大小调的音阶、琶音与和弦的基本练习，还有轮指技术、三度和六度双音练习以及在此基础上形成的双三度与双八度音阶练习等等。由于作品技术内容全面、丰富，因而在我国被认为是每一位钢琴家和钢琴学习者的必学作品。', '2015-06-01', 'img/1005.jpg');
INSERT INTO `book_info` VALUES (1006, '9787530461693', '美国金宝贝早教婴幼儿游戏(0-3岁)', '(美)温蒂玛斯、(美)罗尼科恩莱德曼', '北京科学技术出版社', '育儿', '本书为平日照顾陪伴婴幼儿的父母、（外）祖父母们提供了大量易于掌握的亲子游戏，即可帮助婴幼儿开发各种重要的技能，又可以为家庭带来无穷的乐趣。书中每个游戏都是根据金宝贝游戏&音乐项目（Gymboree Play&Music)中*的早教理念，由金宝贝的资深专家精心设计，并经过30多年早教经验的验证。', '2018-06-01', 'img/1006.jpg');
INSERT INTO `book_info` VALUES (1007, '9787518923342', '医本正经', '懒兔子', '科学技术文献出版社', '医学', '一本会卖萌的中医书。懒兔子用搞笑幽默接地气儿的语言把晦涩难懂的中医普及，它用现代的思维语言讲透老祖宗传下的中医精髓，书中穿插大量师徒二人搞笑奇葩的对话，就像一种对待生活的态度，可以让你笑着读完。\r\n\r\n    万千读者参与的一本书。都市白领、全职妈妈、新时代主妇，收录了来自全国各地粉丝成功治愈家人的案例。不需要专业的医生知识，也不需要专业的技术，打开就会，外行人也能胜任的智慧，一本可以让老公孩子长辈都健康生活的书。', '2017-03-01', 'img/1007.jpg');
INSERT INTO `book_info` VALUES (1008, '9787519720179', '中华人民共和国宪法（2018年3月最新修正版）', '法律出版社', '法律出版社', '法学', '2018年修正版，封面烫金、红皮压纹，含宪法宣誓誓词和宪法宣誓制度决定，国家工作人员宣誓用书，国家宪法日、宪法学习必备，含历次宪法修正案，领导干部、广大党员、企事业单位、学校及人民群众学习领会宪法精神', '2018-03-01', 'img/1008.jpg');
INSERT INTO `book_info` VALUES (1009, '9787201094014', '浮生六记 ', '沈复', '天津人民出版社', '文学', '《浮生六记》是清代文人沈复写作的自传散文。因其以真言述真情，从不刻意造作，得以浑然天成，独树一帜，达“乐而不淫，哀而不伤”之境界，深为后世文人所推崇，流传至今，已成经典。\r\n本次出版选开明书店民国本为底本，考以《雁来红丛报》本、霜枫社民国本、林语堂英译本重新点校；并请张佳玮精心译述，作长文译记为读者导读；独家收录“沈复的一生”（年表）“沈复三十年游历图”，附“光绪三年初版序”“潘麐生题记”“光绪三年初版跋”，便于读者朋友更好读懂中国挚美经典，从中获益。', '2015-08-01', 'img/1009.jpg');
INSERT INTO `book_info` VALUES (1010, '9787506391542', '我喜欢生命本来的样子', '周国平', '作家出版社', '文学', '在茫茫宇宙间，每个人都只有一次生存的机会，都是一个独一无二、不可重复的存在。名声、财产、知识等等是身外之物，人人都可求而得之，但没有人能够代替你感受人生。你死之后，没有人能够代替你再活一次。那么仅有一次的人生到底应该怎么度过？周国平说，每个个体要尊重生命本来的样子，当好自然之子。\r\n\r\n  当面对爱、逆境、孤独、死亡、变故等人生大命题的时候，人应当保持在什么心理状态，是本书*的意义所在。希望本书能为在繁杂的世界里找不到头绪的你，建立强大的灵魂世界。', '2017-02-01', 'img/1010.jpg');
INSERT INTO `book_info` VALUES (1011, '9787535488473', '自在独行', '贾平凹', '长江文艺出版社', '文学', '平凹先生素来喜静，很怕有人来敲他的房门。让他觉得自在的，要么就是行走于西北的大地，要么就是隐居在自己的书房。\r\n\r\n先生其实也喜欢热闹，只是先生的热闹并不是灯火灿烂，而是内心的安宁与独行的自在。\r\n\r\n这本书写情感、聊爱好、谈社会、说人生。有俗世的智慧，也有生活的趣味。\r\n\r\n对于匆匆的路人，平凹先生这部文集只是用来附庸风雅的玩物。但这本书却要写给生命的行者。愿他们能懂得孤独的真义，在生活里多一些从容潇洒。', '2016-05-01', 'img/1011.jpg');
INSERT INTO `book_info` VALUES (1012, '9787532435436', 'book1', 'author1', 'publish1', '文学', 'summary1', '2018-08-02', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1013, '9754352342245', 'book2', 'author2', 'publish2', '文学', 'fdsgdfgfd', '2018-08-03', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1014, '5634765765763', 'book3', 'author3', 'publish3', '文学', 'gwetreh4ytr', '2018-08-02', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1015, '4523452345644', 'book4', 'author4', 'publish4', '文学', 'gdhtrhtrh', '2018-08-23', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1016, '4357573154637', 'book5', 'author5', 'publish5', '文学', 'geye55ygf', '2015-03-26', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1018, '9754476576753', 'book6', 'author6', 'publish6', '文学', 'hretgfgfdg', '2016-10-10', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1019, '7653757567267', 'book7', 'author7', 'publish7', '文学', 'sumgfdgsdgd', '2018-02-08', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1033, '7546387873567', 'book8', 'author8', 'publish8', '文学', 'sumgrertegdfgd', '2018-08-03', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1035, '2546398473821', 'book9', 'author9', 'publish9', '文学', 'teaxvrdfgdfg', '2018-07-31', 'img/book.jpg');
INSERT INTO `book_info` VALUES (1044, '9787532166831', '少时读书', '废名', '上海文艺出版社', '文艺', '书分三辑，一是“中国文章”，关乎文章的写法以及读法，亦是心法。二是“灼灼其华”，关乎诗歌，名字取自诗经，而内容又不囿于此，甚至涉及杜甫的内容要更厚些。三是“格义致知”，更为远阔交融，主要是对于佛家的理解，儒与佛与新思想的相遇，等。', '2018-07-31', 'img/125320847-1_l_4.jpg');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `cart_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`, `book_id`) USING BTREE,
  INDEX `fk_cart_book_idx`(`book_id`) USING BTREE,
  CONSTRAINT `fk_cart_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (20150701, 1001, 5);
INSERT INTO `cart` VALUES (20150701, 1002, 3);
INSERT INTO `cart` VALUES (20150702, 1001, 3);
INSERT INTO `cart` VALUES (20150703, 1004, 4);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comm_id` int(11) NOT NULL AUTO_INCREMENT,
  `comm_score` double(11, 2) NOT NULL DEFAULT 1.00,
  `comm_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `comm_time` datetime(0) DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`comm_id`) USING BTREE,
  INDEX `fk_evaluate_book1_idx`(`book_id`) USING BTREE,
  INDEX `fk_evaluate_user1_idx`(`user_id`) USING BTREE,
  INDEX `fk_evaluate_order1_idx`(`order_id`) USING BTREE,
  CONSTRAINT `fk_comment_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 4.80, '这本书很好，各方面都解释的很清楚', '2018-07-18 11:29:03', 1002, 203641, 20150702);
INSERT INTO `comment` VALUES (2, 4.80, '这是一本让人回忆青春年华的书', '2018-07-18 11:58:47', 1001, 203641, 20150702);
INSERT INTO `comment` VALUES (3, 4.70, '算法导论的结构清晰，内容不错，就是有些代码存在一些小问题', '2018-07-27 10:00:18', 1002, 203642, 20150703);
INSERT INTO `comment` VALUES (4, 4.80, '教宝宝玩很棒，书的内容不错', '2018-08-07 16:01:02', 1006, 203643, 20150703);
INSERT INTO `comment` VALUES (5, 4.70, '看了之后很多小病都知道如何去治，漫画的形式让人一目了然。', '2018-08-07 16:02:07', 1007, 203643, 20150703);
INSERT INTO `comment` VALUES (7, 4.60, 'test111111111111111111111111111111145422222266666666666666666666666666666666666666666666666666', '2018-08-21 15:03:43', 1008, 203646, 20150704);
INSERT INTO `comment` VALUES (8, 4.70, 'test2222222222222222222222222222222', '2018-07-12 15:04:37', 1008, 203648, 20150705);
INSERT INTO `comment` VALUES (9, 4.90, 'test3333333333333333333333333333333', '2018-05-30 15:05:28', 1008, 203655, 20150707);

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `order_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `item_quantity` int(11) DEFAULT NULL,
  `item_price` decimal(10, 2) DEFAULT 0.00,
  PRIMARY KEY (`order_id`, `book_id`) USING BTREE,
  INDEX `fk_item_book_idx`(`book_id`) USING BTREE,
  CONSTRAINT `fk_item_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_item_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (203641, 1001, 2, 37.40);
INSERT INTO `item` VALUES (203641, 1002, 1, 102.40);
INSERT INTO `item` VALUES (203642, 1002, 11, 102.40);
INSERT INTO `item` VALUES (203643, 1006, 6, 73.30);
INSERT INTO `item` VALUES (203643, 1007, 4, 36.40);
INSERT INTO `item` VALUES (203644, 1005, 11, 23.30);
INSERT INTO `item` VALUES (203645, 1003, 8, 23.30);
INSERT INTO `item` VALUES (203646, 1004, 6, 23.30);
INSERT INTO `item` VALUES (203647, 1005, 1, 23.30);
INSERT INTO `item` VALUES (203647, 1019, 1, 23.30);
INSERT INTO `item` VALUES (203648, 1008, 1, 23.30);
INSERT INTO `item` VALUES (203649, 1009, 1, 23.30);
INSERT INTO `item` VALUES (203649, 1033, 1, 23.30);
INSERT INTO `item` VALUES (203650, 1010, 1, 23.30);
INSERT INTO `item` VALUES (203650, 1035, 1, 23.30);
INSERT INTO `item` VALUES (203651, 1011, 1, 23.30);
INSERT INTO `item` VALUES (203651, 1014, 1, 32.00);
INSERT INTO `item` VALUES (203652, 1007, 3, 42.00);
INSERT INTO `item` VALUES (203652, 1012, 1, 23.30);
INSERT INTO `item` VALUES (203653, 1007, 5, 12.00);
INSERT INTO `item` VALUES (203653, 1013, 1, 23.30);
INSERT INTO `item` VALUES (203654, 1008, 2, 42.00);
INSERT INTO `item` VALUES (203654, 1014, 1, 23.30);
INSERT INTO `item` VALUES (203655, 1003, 1, 23.00);
INSERT INTO `item` VALUES (203655, 1015, 1, 23.30);
INSERT INTO `item` VALUES (203656, 1005, 1, 23.30);
INSERT INTO `item` VALUES (203656, 1009, 1, 64.00);
INSERT INTO `item` VALUES (203656, 1010, 2, 23.00);
INSERT INTO `item` VALUES (203656, 1018, 1, 23.30);
INSERT INTO `item` VALUES (203657, 1003, 7, 53.00);
INSERT INTO `item` VALUES (203658, 1014, 2, 23.00);
INSERT INTO `item` VALUES (203659, 1005, 1, 64.00);
INSERT INTO `item` VALUES (203660, 1035, 2, 23.00);
INSERT INTO `item` VALUES (203661, 1015, 1, 42.00);
INSERT INTO `item` VALUES (203662, 1019, 1, 23.00);
INSERT INTO `item` VALUES (203663, 1019, 1, 64.00);
INSERT INTO `item` VALUES (203664, 1003, 2, 34.00);
INSERT INTO `item` VALUES (203665, 1006, 5, 45.00);
INSERT INTO `item` VALUES (203666, 1004, 1, 47.00);
INSERT INTO `item` VALUES (203667, 1015, 1, 42.00);
INSERT INTO `item` VALUES (203668, 1004, 8, 52.00);
INSERT INTO `item` VALUES (203669, 1007, 5, 25.00);
INSERT INTO `item` VALUES (203670, 1014, 1, 34.00);
INSERT INTO `item` VALUES (203671, 1009, 3, 54.00);
INSERT INTO `item` VALUES (203672, 1004, 2, 57.00);
INSERT INTO `item` VALUES (203673, 1008, 1, 64.00);
INSERT INTO `item` VALUES (203674, 1005, 3, 72.00);
INSERT INTO `item` VALUES (203675, 1006, 2, 53.00);
INSERT INTO `item` VALUES (203676, 1003, 1, 88.00);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_total` decimal(10, 2) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `order_state` tinyint(4) DEFAULT NULL COMMENT '0、1、2、3',
  `order_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `uid_idx`(`user_id`) USING BTREE,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 203677 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (203641, 177.00, 20150702, 3, 'bob', '湖南省湘潭市湘潭大学', '18396546577', '2018-07-14 10:22:56');
INSERT INTO `orders` VALUES (203642, 102.40, 20150703, 3, 'alice', '湖南省长沙市长沙理工大学', '18164346366', '2018-07-25 10:23:11');
INSERT INTO `orders` VALUES (203643, 109.70, 20150702, 1, 'mack', '湖南省岳阳市湖南理工学院', '18354354656', '2018-08-03 15:55:05');
INSERT INTO `orders` VALUES (203644, 24.50, 20150704, 3, 'test', 'address', '34354555555', '2018-08-08 11:03:20');
INSERT INTO `orders` VALUES (203645, 45.30, 20150704, 2, 'test', 'address', '34354555555', '2018-08-15 11:06:35');
INSERT INTO `orders` VALUES (203646, 54.50, 20150703, 3, 'test', 'address', '34354555555', '2018-06-13 11:06:44');
INSERT INTO `orders` VALUES (203647, 54.50, 20150702, 3, 'test', 'address', '34354555555', '2018-05-23 11:06:51');
INSERT INTO `orders` VALUES (203648, 54.50, 20150703, 3, 'test', 'address', '34354555555', '2018-04-18 11:06:58');
INSERT INTO `orders` VALUES (203649, 54.50, 20150703, 3, 'test', 'address', '34354555555', '2018-03-23 11:07:04');
INSERT INTO `orders` VALUES (203650, 54.50, 20150701, 2, 'test', 'address', '34354555555', '2018-02-28 11:07:12');
INSERT INTO `orders` VALUES (203651, 54.50, 20150701, 3, 'test', 'address', '34354555555', '2018-01-23 11:07:19');
INSERT INTO `orders` VALUES (203652, 54.50, 20150702, 3, 'test', 'address', '34354555555', '2018-02-12 11:07:26');
INSERT INTO `orders` VALUES (203653, 54.50, 20150703, 3, 'test', 'address', '34354555555', '2018-03-08 11:07:33');
INSERT INTO `orders` VALUES (203654, 54.50, 20150704, 2, 'test', 'address', '34354555555', '2018-06-13 11:07:41');
INSERT INTO `orders` VALUES (203655, 54.50, 20150704, 2, 'test', 'address', '34354555555', '2018-05-24 11:07:48');
INSERT INTO `orders` VALUES (203656, 54.50, 20150703, 3, 'test', 'address', '34354555555', '2018-04-20 11:07:55');
INSERT INTO `orders` VALUES (203657, 23.30, 20150705, 3, 'gsd', 'greh', '6542646456', '2018-08-31 11:19:24');
INSERT INTO `orders` VALUES (203658, 45.30, 20150706, 3, 'fd', 'sfhgdf', '4663463', '2018-01-16 11:19:28');
INSERT INTO `orders` VALUES (203659, 23.50, 20150707, 4, 'fdg', 'gfh', '65652562', '2018-02-21 11:19:35');
INSERT INTO `orders` VALUES (203660, 63.00, 20150708, 3, 'gf', 'sfhfg', '65246466', '2018-08-08 11:20:30');
INSERT INTO `orders` VALUES (203661, 4.00, 20150709, 3, 'gds', 'gsdg', '2546456', '2018-02-28 11:20:34');
INSERT INTO `orders` VALUES (203662, 54.00, 20150710, 2, 'bs', 'fsg', '52464526', '2018-05-23 11:19:57');
INSERT INTO `orders` VALUES (203663, 54.00, 20150705, 3, 'gs', 'bsdf', '6526256', '2018-06-13 11:20:39');
INSERT INTO `orders` VALUES (203664, 52.00, 20150706, 3, 'fs', 'gfds', '526562', '2018-06-28 11:20:03');
INSERT INTO `orders` VALUES (203665, 53.00, 20150708, 2, 'sg', 'grdsg', '526526', '2018-03-28 11:20:44');
INSERT INTO `orders` VALUES (203666, 25.00, 20150707, 3, 'gf', 'sfd', '54637665', '2018-04-05 11:19:46');
INSERT INTO `orders` VALUES (203667, 45.00, 20150709, 3, 'ha', 'gsfdg', '6376576', '2018-06-30 11:20:50');
INSERT INTO `orders` VALUES (203668, 32.00, 20150710, 3, 'af', 'bfsdf', '637537643', '2018-08-10 11:20:56');
INSERT INTO `orders` VALUES (203669, 75.00, 20150704, 3, 'gfa', 'sdfg', '62654563', '2018-02-27 11:21:01');
INSERT INTO `orders` VALUES (203670, 37.00, 20150706, 3, 'ag', 'gsdf', '2454646', '2018-03-22 11:19:40');
INSERT INTO `orders` VALUES (203671, 75.00, 20150705, 0, 'ad', 'sf', '56546265', '2018-04-13 11:21:07');
INSERT INTO `orders` VALUES (203672, 37.00, 20150708, 3, 'gr', 'fg', '652647754', '2018-06-20 11:21:13');
INSERT INTO `orders` VALUES (203673, 96.00, 20150707, 3, 'rgr', 'resg', '75462565', '2018-05-22 11:21:19');
INSERT INTO `orders` VALUES (203674, 57.00, 20150709, 3, 'eg', 'sfdg', '5473675', '2018-07-03 11:20:10');
INSERT INTO `orders` VALUES (203675, 63.00, 20150706, 4, 'e', 'fdg', '73462546', '2018-07-12 11:21:25');
INSERT INTO `orders` VALUES (203676, 83.00, 20150705, 3, 'gds', 'gsdfg', '65464736', '2018-02-21 11:21:33');

-- ----------------------------
-- Table structure for receive
-- ----------------------------
DROP TABLE IF EXISTS `receive`;
CREATE TABLE `receive`  (
  `recv_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `recv_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `recv_address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `recv_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`recv_id`) USING BTREE,
  INDEX `uid_idx`(`user_id`) USING BTREE,
  CONSTRAINT `fk_receive_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receive
-- ----------------------------
INSERT INTO `receive` VALUES (1, 20150702, 'bob', '湖南省湘潭市湘潭大学', '18396546577');
INSERT INTO `receive` VALUES (2, 20150703, 'alice', '湖南省长沙市长沙理工大学', '18164346366');
INSERT INTO `receive` VALUES (3, 20150702, 'mack', '湖南省岳阳市湖南理工学院', '18354354656');
INSERT INTO `receive` VALUES (4, 20150704, 'lucy', '湖北省武汉市华中科技大学', '18293453532');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20150711 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (20150701, 'admin', '123456', NULL);
INSERT INTO `user` VALUES (20150702, 'bob', '357862', NULL);
INSERT INTO `user` VALUES (20150703, 'alice', '862954', NULL);
INSERT INTO `user` VALUES (20150704, 'lucy', '465654', NULL);
INSERT INTO `user` VALUES (20150705, 'jack', 'rwt4334', NULL);
INSERT INTO `user` VALUES (20150706, 'ehite', '34rwsfs', NULL);
INSERT INTO `user` VALUES (20150707, 'jtrtg', '4655tdrg', NULL);
INSERT INTO `user` VALUES (20150708, 'ytret', '5sdrgd', NULL);
INSERT INTO `user` VALUES (20150709, 'yurer', 'gdr4re', NULL);
INSERT INTO `user` VALUES (20150710, 'ithsts', 'y545ter', NULL);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` int(11) NOT NULL,
  `user_sex` tinyint(4) DEFAULT NULL COMMENT '0或1',
  `user_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `fk_info_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (20150701, 1, '18275839209', 'admin@gmail.com');
INSERT INTO `user_info` VALUES (20150702, 1, '18396546577', 'bob@qq.com');
INSERT INTO `user_info` VALUES (20150703, 0, '18164346366', 'alice@qq.com');
INSERT INTO `user_info` VALUES (20150704, 0, '18293453532', 'lucy@qq.com');

-- ----------------------------
-- View structure for v_book
-- ----------------------------
DROP VIEW IF EXISTS `v_book`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_book` AS select `book_info`.`book_id` AS `book_id`,`book_info`.`book_isbn` AS `book_isbn`,`book_info`.`book_name` AS `book_name`,`book_info`.`book_author` AS `book_author`,`book_info`.`book_publish` AS `book_publish`,`book_info`.`book_kind` AS `book_kind`,`book_info`.`book_summary` AS `book_summary`,`book_info`.`book_publtime` AS `book_publtime`,`book_info`.`book_cover` AS `book_cover`,`book`.`book_price` AS `book_price`,`book`.`book_storage` AS `book_storage`,`book`.`book_volume` AS `book_volume`,`book`.`book_score` AS `book_score`,`book`.`book_delete` AS `book_delete` from (`book_info` left join `book` on((`book_info`.`book_id` = `book`.`book_id`))) order by `book_info`.`book_id` desc;

-- ----------------------------
-- View structure for v_comment
-- ----------------------------
DROP VIEW IF EXISTS `v_comment`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_comment` AS select `comment`.`comm_id` AS `comm_id`,`comment`.`comm_score` AS `comm_score`,`comment`.`comm_content` AS `comm_content`,`comment`.`comm_time` AS `comm_time`,`comment`.`order_id` AS `order_id`,`comment`.`book_id` AS `book_id`,`comment`.`user_id` AS `user_id`,`user`.`user_name` AS `user_name` from (`comment` join `user`) where (`comment`.`user_id` = `user`.`user_id`);

-- ----------------------------
-- View structure for v_item_book
-- ----------------------------
DROP VIEW IF EXISTS `v_item_book`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_item_book` AS select `item`.`order_id` AS `order_id`,`item`.`book_id` AS `book_id`,`book_info`.`book_name` AS `book_name`,`book_info`.`book_author` AS `book_author`,`book_info`.`book_publish` AS `book_publish`,`book_info`.`book_kind` AS `book_kind`,`book_info`.`book_cover` AS `book_cover`,`item`.`item_quantity` AS `item_quantity`,`item`.`item_price` AS `item_price`,(`item`.`item_quantity` * `item`.`item_price`) AS `subtotal`,`orders`.`order_time` AS `order_time` from ((`item` join `book_info`) join `orders`) where ((`item`.`book_id` = `book_info`.`book_id`) and (`item`.`order_id` = `orders`.`order_id`)) order by `orders`.`order_time` desc,`item`.`order_id` desc,`item`.`book_id`;

-- ----------------------------
-- View structure for v_show_all_book
-- ----------------------------
DROP VIEW IF EXISTS `v_show_all_book`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_show_all_book` AS select `a`.`book_id` AS `book_id`,`a`.`book_price` AS `book_price`,`a`.`book_storage` AS `book_storage`,`a`.`book_volume` AS `book_volume`,`a`.`book_score` AS `book_score`,`a`.`book_delete` AS `book_delete`,`b`.`book_isbn` AS `book_isbn`,`b`.`book_name` AS `book_name`,`b`.`book_author` AS `book_author`,`b`.`book_publish` AS `book_publish`,`b`.`book_kind` AS `book_kind`,`b`.`book_summary` AS `book_summary`,`b`.`book_publtime` AS `book_publtime`,`b`.`book_cover` AS `book_cover` from (`book` `a` left join `book_info` `b` on((`a`.`book_id` = `b`.`book_id`)));

-- ----------------------------
-- View structure for v_show_all_cart
-- ----------------------------
DROP VIEW IF EXISTS `v_show_all_cart`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_show_all_cart` AS select `a`.`user_id` AS `user_id`,`a`.`book_id` AS `book_id`,`a`.`cart_quantity` AS `cart_quantity`,`b`.`book_price` AS `book_price`,`c`.`book_name` AS `book_name`,`c`.`book_cover` AS `book_cover` from ((`cart` `a` left join `book` `b` on((`a`.`book_id` = `b`.`book_id`))) left join `book_info` `c` on((`b`.`book_id` = `c`.`book_id`)));

-- ----------------------------
-- View structure for v_show_all_orders
-- ----------------------------
DROP VIEW IF EXISTS `v_show_all_orders`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_show_all_orders` AS select `a`.`order_id` AS `order_id`,`a`.`order_total` AS `order_total`,`a`.`user_id` AS `user_id`,`a`.`order_state` AS `order_state`,`a`.`order_name` AS `order_name`,`a`.`order_address` AS `order_address`,`a`.`order_phone` AS `order_phone`,`a`.`order_time` AS `order_time`,`b`.`book_id` AS `book_id`,`b`.`item_quantity` AS `item_quantity`,`b`.`item_price` AS `item_price` from (`orders` `a` left join `item` `b` on((`a`.`order_id` = `b`.`order_id`)));

-- ----------------------------
-- View structure for v_show_all_user
-- ----------------------------
DROP VIEW IF EXISTS `v_show_all_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_show_all_user` AS select `a`.`user_id` AS `user_id`,`a`.`user_name` AS `user_name`,`a`.`user_password` AS `user_password`,`a`.`user_head` AS `user_head`,`b`.`user_sex` AS `user_sex`,`b`.`user_phone` AS `user_phone`,`b`.`user_email` AS `user_email` from (`user` `a` left join `user_info` `b` on((`a`.`user_id` = `b`.`user_id`)));

-- ----------------------------
-- View structure for v_volume_count
-- ----------------------------
DROP VIEW IF EXISTS `v_volume_count`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_volume_count` AS select `book_info`.`book_kind` AS `book_kind`,cast(`v_item_book`.`order_time` as date) AS `order_time`,sum(`v_item_book`.`item_quantity`) AS `total_volume` from (`book_info` left join `v_item_book` on((`book_info`.`book_kind` = `v_item_book`.`book_kind`))) group by `book_info`.`book_kind`,cast(`v_item_book`.`order_time` as date);

SET FOREIGN_KEY_CHECKS = 1;
