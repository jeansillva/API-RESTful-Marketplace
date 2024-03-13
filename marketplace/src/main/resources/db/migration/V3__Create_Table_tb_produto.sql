CREATE TABLE IF NOT EXISTS `tb_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `qtd` bigint DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `vendedor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3fwdiywc5uytkb4no6a3o7mng` (`cliente_id`),
  KEY `FKko71acawtnmtxppuygwvirdxh` (`vendedor_id`),
  CONSTRAINT `FK3fwdiywc5uytkb4no6a3o7mng` FOREIGN KEY (`cliente_id`) REFERENCES `tb_clientes` (`id`),
  CONSTRAINT `FKko71acawtnmtxppuygwvirdxh` FOREIGN KEY (`vendedor_id`) REFERENCES `tb_vendedor` (`id`)
);