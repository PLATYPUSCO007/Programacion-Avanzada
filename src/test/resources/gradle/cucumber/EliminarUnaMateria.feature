Feature: Como usuario quiero eliminar una materia

  Scenario: Eliminar una materia 
    Given Get a session
    When creo la materia 
    And elimino la materia
    Then verifico la bd
