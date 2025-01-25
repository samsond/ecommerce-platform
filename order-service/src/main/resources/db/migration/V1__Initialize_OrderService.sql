-- V1__Initialize_OrderService.sql
CREATE TABLE inventory (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           product_id BIGINT NOT NULL,
                           stock INT NOT NULL,
                           last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        customer_id BIGINT NOT NULL,
                        product_id BIGINT NOT NULL,
                        quantity INT NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(255) NOT NULL DEFAULT 'PENDING'
);

-- Insert initial inventory data
INSERT INTO inventory (product_id, stock) VALUES
                                              (1, 200), -- Apple iPhone 15 Pro
                                              (2, 150), -- Samsung Galaxy S23 Ultra
                                              (3, 300), -- Sony WH-1000XM5 Headphones
                                              (4, 50),  -- MacBook Pro 16"
                                              (5, 120), -- Dell XPS 13
                                              (6, 500), -- Nintendo Switch OLED
                                              (7, 100), -- Sony PlayStation 5
                                              (8, 800), -- Kindle Paperwhite
                                              (9, 600), -- Apple AirPods Pro (2nd Gen)
                                              (10, 700), -- Instant Pot Duo 7-in-1
                                              (11, 400), -- Bose SoundLink Revolve+ II
                                              (12, 80), -- Canon EOS R6 Mark II
                                              (13, 350), -- Fitbit Charge 5
                                              (14, 900), -- Logitech MX Master 3S
                                              (15, 250), -- LEGO Star Wars Millennium Falcon
                                              (16, 75),  -- Dyson V15 Detect Vacuum
                                              (17, 500), -- KitchenAid Stand Mixer
                                              (18, 90),  -- Samsung 4K UHD Smart TV
                                              (19, 300), -- Patagonia Nano Puff Jacket
                                              (20, 400); -- The North Face Borealis Backpack
