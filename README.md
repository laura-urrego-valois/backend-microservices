# Examen final de la materia Especialización en Backend I

## Resiliencia - Resilence4j
Se debe seleccionar uno de los servicios (preferentemente el que consideres que será más utilizado) y adaptarlo para que el mismo sea tolerante a fallos.

Para lo anterior deberás:

--> Definir esquema de resiliencia, implementar retry, un método fallback y configurar las reglas del circuito.

--> Describir la solución de dicha implementación, planteando un supuesto escenario que requiera implementar este patrón, justificándolo en un comentario o en el Readme del repositorio perteneciente al proyecto.

## El servicio seleccionado para que sea tolerante a fallos es el CatalogService.

El CatalogService es un servicio muy importante, ya que es el punto de entrada para que los usuarios accedan a información sobre películas y series. Por eso, es importante que sea tolerante
a fallos.
Si el CatalogService falla, los usuarios no podrán acceder a la información que necesitan. Esto podría ocasionar inconformidades e insatisfacción a los usuarios, y la imagen de la
aplicación se vería gravemente afectada así que es necesario pensar en una forma de evitar las interrupciones innecesarias.

Es importante tener en cuenta que el CatalogService depende de otros servicios para obtener información (películas y series). Si uno de estos servicios falla, el CatalogService también
podría verse comprometido. Es también por esta razón, que se hace necesario que el CatalogService sea capaz de manejar situaciones en las que sus servicios dependientes no estén disponibles.

Para concluir, la elección de CatalogService como el servicio tolerante a fallos contribuirá a mantener un funcionamiento fluido de la aplicación. Esta medida beneficia tanto a los usuarios
como a la integridad general del sistema.
