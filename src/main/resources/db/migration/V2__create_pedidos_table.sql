CREATE TABLE pedidos (
                         id SERIAL PRIMARY KEY,
                         cliente_id INT NOT NULL,
                         valor_total DECIMAL(10,2) NOT NULL,
                         data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);
