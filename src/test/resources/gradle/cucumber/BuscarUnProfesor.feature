Feature: Como usuario quiero buscar un Profesor

  Scenario: Buscar un Profesor
    Given iniciar una session
    When registro el Profesor
    And busco el Profesor en la BD
    Then valido la busqueda
