-- Create the category table
CREATE TABLE categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

-- Create the products table with a foreign key to the categories table
CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(10, 2) NOT NULL,
                          category_id BIGINT,
                          FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Insert sample categories
INSERT INTO categories (name) VALUES
                                  ('Electronics'),
                                  ('Computers'),
                                  ('Gaming'),
                                  ('Home Appliances'),
                                  ('Books');

-- Insert sample data for products with category links
INSERT INTO products (name, description, price, category_id) VALUES
                                                                 ('Apple iPhone 15 Pro', 'The latest iPhone with A17 Bionic chip, titanium design, and advanced camera features.', 999.99,
                                                                  (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                 ('Samsung Galaxy S23 Ultra', 'Flagship smartphone with Snapdragon 8 Gen 2 processor, 200MP camera, and S Pen.', 1199.99,
                                                                  (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                 ('Sony WH-1000XM5 Headphones', 'Noise-canceling wireless headphones with superior sound quality and 30-hour battery life.', 399.99,
                                                                  (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                 ('MacBook Pro 16"', 'High-performance laptop with M2 Max chip, Liquid Retina XDR display, and all-day battery life.', 2499.99,
                                                                  (SELECT id FROM categories WHERE name = 'Computers')),
                                                                 ('Dell XPS 13', 'Compact and powerful ultrabook with 13.4" InfinityEdge display and Intel Core i7 processor.', 1299.99,
                                                                  (SELECT id FROM categories WHERE name = 'Computers')),
                                                                 ('Nintendo Switch OLED', 'Gaming console with vibrant 7-inch OLED screen and enhanced audio for portable or docked play.', 349.99,
                                                                  (SELECT id FROM categories WHERE name = 'Gaming')),
                                                                 ('Sony PlayStation 5', 'Next-gen gaming console with lightning-fast load times and ray-tracing support.', 499.99,
                                                                  (SELECT id FROM categories WHERE name = 'Gaming')),
                                                                 ('Kindle Paperwhite', 'Waterproof e-reader with adjustable warm light and 10 weeks of battery life.', 139.99,
                                                                  (SELECT id FROM categories WHERE name = 'Books')),
                                                                 ('Apple AirPods Pro (2nd Gen)', 'Wireless earbuds with Active Noise Cancellation and Adaptive Transparency.', 249.99,
                                                                  (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                 ('Instant Pot Duo 7-in-1', 'Multi-cooker with 7 cooking functions including pressure cook, slow cook, and steam.', 89.99,
                                                                  (SELECT id FROM categories WHERE name = 'Home Appliances')),
                                                                 ('Bose SoundLink Revolve+ II', 'Portable Bluetooth speaker with deep, immersive sound and up to 17 hours of battery life.', 329.99,
                                                                  (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                 ('Canon EOS R6 Mark II', 'Full-frame mirrorless camera with 24.2MP sensor and 4K video recording.', 2499.99,
                                                                  (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                 ('Fitbit Charge 5', 'Fitness tracker with heart rate monitoring, built-in GPS, and sleep tracking.', 149.99,
                                                                  (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                 ('Logitech MX Master 3S', 'Ergonomic wireless mouse with ultra-fast scrolling and customizable buttons.', 99.99,
                                                                  (SELECT id FROM categories WHERE name = 'Computers')),
                                                                 ('LEGO Star Wars Millennium Falcon', '1,351-piece model building kit of the iconic Millennium Falcon.', 159.99,
                                                                  (SELECT id FROM categories WHERE name = 'Books')),
                                                                 ('Dyson V15 Detect Vacuum', 'Cordless vacuum with laser detection for hidden dust and powerful suction.', 699.99,
                                                                  (SELECT id FROM categories WHERE name = 'Home Appliances')),
                                                                 ('KitchenAid Stand Mixer', '5-quart mixer with 10 speeds, perfect for baking and cooking.', 349.99,
                                                                  (SELECT id FROM categories WHERE name = 'Home Appliances')),
                                                                 ('Samsung 4K UHD Smart TV', '65-inch QLED TV with Quantum Dot technology and built-in Alexa.', 1499.99,
                                                                  (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                 ('Patagonia Nano Puff Jacket', 'Lightweight, water-resistant insulated jacket made with recycled materials.', 199.99,
                                                                  (SELECT id FROM categories WHERE name = 'Home Appliances')),
                                                                 ('The North Face Borealis Backpack', 'Versatile backpack with laptop compartment and FlexVent suspension system.', 99.99,
                                                                  (SELECT id FROM categories WHERE name = 'Books'));
