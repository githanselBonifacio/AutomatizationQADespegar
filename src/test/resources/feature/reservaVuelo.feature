Feature: consultar y reservar vuelo
  yo como cliente
  quiero consultar y reservar vuelos
  para poder comprar el mejor tiquete de vuelo de acuerdo a mis requerimietos del viaje

  Scenario: verificar categoria de asientos por edad
    Given el cliente está en el formulario de consulta de vuelos
    When el cliente llene los campos del formulario con fechas disponibles y lo envíe
    Then se deberá mostrar en el detalle de cada opción de compra el número de asientos de adultos y niños según edad

  Scenario: verificar precio final de los tiquetes
    Given el cliente está en el formulario de consulta de vuelos
    When el cliente ingrese los datos en los campos del formulario con fechas disponibles y lo envíe
    Then se deberá mostrar en el detalle de cada opción de compra el precio final de los tiquetes