# Examen final de la materia Especialización en Backend I

## Resiliencia - Resilence4j
Se debe seleccionar uno de los servicios (preferentemente el que consideres que será más utilizado) y adaptarlo para que el mismo sea tolerante a fallos.

Para lo anterior deberás:

--> Describir la solución de dicha implementación, planteando un supuesto escenario que requiera implementar este patrón, justificándolo en un comentario o en el Readme del repositorio perteneciente al proyecto.

## El servicio seleccionado para que sea tolerante a fallos es catalog-service.

El catalog-service es un servicio muy importante, ya que es el punto de entrada para que los usuarios accedan a información sobre películas y series. Por eso, es importante que sea tolerante a fallos.

Supongamos que los servicios de películas y series pueden experimentar interrupciones temporales o sobrecarga debido a la alta demanda. Si no se utiliza un Circuit Breaker, la aplicación intentaría realizar llamadas a estos servicios incluso cuando estén experimentando problemas, lo que podría aumentar la carga en los servicios y hacer que la aplicación sea menos resistente a fallos.

En el escenario dado, la aplicación utiliza el Circuit Breaker para protegerla de los servicios de películas o series que no responden adecuadamente. Si estos servicios no responden, el Circuit Breaker abrirá el circuito y la aplicación utilizará los métodos de fallback findAllEmpty evitando así que la aplicación colapse o se degrade debido a problemas en los servicios.

Para concluir, la elección de catalog-service como el servicio tolerante a fallos contribuirá a mantener un funcionamiento fluido de la aplicación. Esta medida beneficia tanto a los usuarios como a la integridad general del sistema.
