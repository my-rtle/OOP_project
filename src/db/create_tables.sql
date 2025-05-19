CREATE TABLE IF NOT EXISTS category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    color VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    description TEXT,
    date DATE,
    start_time TIME,
    end_time TIME,
    location VARCHAR(255),
    alarm_time DATETIME,
    category_id INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS alarm_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    schedule_id INT,
    triggered_at DATETIME,
    status VARCHAR(20),
    FOREIGN KEY (schedule_id) REFERENCES schedule(id)
);