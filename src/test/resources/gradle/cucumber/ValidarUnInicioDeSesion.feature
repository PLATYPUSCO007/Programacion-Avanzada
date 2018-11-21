Feature: Como usuario quiero validar las credenciales de un usuario

  Scenario: Registro un usuario y verifico sus credenciales en la bd al iniciar sesion
    Given obtenemos una nueva sesion
    When registro un nuevo usuario
    And inicio sesion en el sistema
    Then valido las credenciales en la bd
