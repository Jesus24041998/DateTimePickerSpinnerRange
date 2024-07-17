# Biblioteca de TimePicker con Rangos Personalizados

## Descripción
Esta biblioteca permite modificar el componente TimePicker de Android para agregarle tres tipos de rangos personalizados para las horas y los minutos. Con esta herramienta, podrás ajustar el selector de tiempo a tus necesidades específicas de una manera sencilla y eficiente.

![Not found](https://img.shields.io/badge/Maven%20Central-1.0.0-orange?link=https%3A%2F%2Fcentral.sonatype.com%2Fartifact%2Fio.github.jesus24041998%2FDateTimePickerSpinnerRangeLibrary)

## Características

### Rangos de Horas:
- De 1 en 1
- De 2 en 2
- De 4 en 4

### Rangos de Minutos:
- De 1 en 1
- De 5 en 5
- De 10 en 10

## Instalación
Añade la dependencia en tu archivo build.gradle:

```
dependencies {
    implementation("io.github.jesus24041998:DateTimePickerSpinnerRangeLibrary:1.0.0")
}
```

## Ejemplo de uso

```
DateTimePickerDialogSpinnerRange(applicationContext,supportFragmentManager,true,HoursRange.NORMAL_HOURS,MinutesRange.NORMAL_MINUTES, 6, 15) { _: TimePicker, hour: Int, minute: Int ->
    //Recoge la hora/minuto
}
```

## Contribuir
¡Las contribuciones son bienvenidas! Si tienes alguna mejora o encuentras un problema, por favor abre un issue o envía un pull request.

## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

#

# Custom Range TimePicker Library

## Description
This library allows you to modify the Android TimePicker component to add three types of custom ranges for hours and minutes. With this tool, you can easily and efficiently adjust the time picker to meet your specific needs.

## Features
### Hour Ranges:
- By 1 hour
- By 2 hours
- By 4 hours

### Minute Ranges:
- By 1 minute
- By 5 minutes
- By 10 minutes

## Installation
Add the dependency in your build.gradle file:

```
dependencies {
    implementation("io.github.jesus24041998:DateTimePickerSpinnerRangeLibrary:1.0.0")
}
```

## Usage

```
DateTimePickerDialogSpinnerRange(applicationContext,supportFragmentManager,true,HoursRange.NORMAL_HOURS,MinutesRange.NORMAL_MINUTES, 6, 15) { _: TimePicker, hour: Int, minute: Int ->
    //Get the hour/minute
}
```
## Contribution
Contributions are welcome! If you have any improvements or find an issue, please open an issue or submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

![Not found](/DateTimePickerSpinnerRangeLibrary/assets/images/CapturaNormal.png) ![Not found](/DateTimePickerSpinnerRangeLibrary/assets/images/Captura2horas5minutos.png) ![Not found](/DateTimePickerSpinnerRangeLibrary/assets/images/Captura4horas10minutos.png)