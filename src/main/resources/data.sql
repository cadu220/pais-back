insert into usuario( login, senha, nome, administrador)
values (  'convidado', '$2a$10$iJ7Bo1MhdDgE3l0Y34JBm.wWjJsqvgBiToXjfX1hB4X.nh7QUZloS', 'Usuário convidado', 0);
--
insert into usuario( login, senha, nome, administrador)
values ( 'admin', '$2a$10$rIWHjodePOax4pg2SuJYt.DSs77ss.Z3mhbqgm61qh.Gc5L6guVBS', 'Gestor', 1);

insert into pais( nome, sigla, gentilico)
values ( 'Brasil', 'BR', 'Brasileiro');

insert into pais( nome, sigla, gentilico)
values ( 'Argentina', 'AR', 'Argentino');

insert into pais( nome, sigla, gentilico)
values ( 'Alemanha', 'AL', 'Alemão');