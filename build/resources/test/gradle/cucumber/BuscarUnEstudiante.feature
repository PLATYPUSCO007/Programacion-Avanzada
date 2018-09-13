Feature: Como usuario quiero buscar un estudiante

  Scenario: Buscar un estudiante
    Given iniciar sesion
    When creo el estudiante
    And busco el estudiante en la BD
    Then imprimo la busqueda
