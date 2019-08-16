-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-08-2019 a las 00:49:34
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `concesionario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auto`
--

CREATE TABLE `auto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(30) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `placa` varchar(6) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `marca` varchar(20) DEFAULT NULL,
  `id_empleado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `auto`
--

INSERT INTO `auto` (`id`, `nombre`, `descripcion`, `precio`, `placa`, `fecha_ingreso`, `marca`, `id_empleado`) VALUES
(1, 'Mustang GT 2018', 'Mate', 119000000, 'MKG215', '2019-06-30', 'Ford', 1),
(2, 'Fiesta 2019', 'Gris', 60000000, 'FLG115', '2019-07-01', 'Ford', 1),
(3, 'Beat 2019', 'Gris', 38000000, 'MTX976', '2019-08-15', 'Chevrolet', 2),
(4, 'Rio 2019', 'Blanco', 31000000, 'QKG117', '2019-07-10', 'Kia', 2),
(5, 'CAASD', 'CARDS', 422454534, 'ACSDAD', '2019-08-21', 'ACDSACSD', 1),
(6, 'CAASD', 'CARDS', 422454534, 'ACSDAD', '2019-08-21', 'ACDSACSD', 1),
(7, 'CAASD', 'CARDS', 422454, 'ACSDAD', '2019-08-21', 'ACDSACSD', 1),
(8, 'CAASD', 'CARDS', 422454, 'ACSDAD', '2019-08-21', 'ACDSACSD', 1),
(9, 'ASFDASDF', 'AFDSASDF', 645456645, 'AFSDAS', '2019-12-31', 'ADSFASDFASDF', 2),
(10, 'CAASD', 'CARDS', 4224545, 'ACSDAD', '2019-08-21', 'ACDSACSD', 1),
(11, 'asadsasfddsf', 'asddsaffdsasfda', 44524526, 'asdfsd', '2019-08-21', 'asdd', 2),
(12, 'asadsasfddsf', 'asddsaffdsasfda', 4452424, 'asdfsd', '2019-08-21', 'asdd', 2),
(13, 'asdfadsfdas', 'asdfasd', 45456345, 'fdgsds', '2019-08-15', 'sdfsdfsfdsdfgfsd', 2),
(14, 'asdfadsfdas', 'asdfasd', 45456345, 'fdgsds', '2019-08-15', 'sdfsdfsfdsdfgfsd', 2),
(15, 'asdfadsfdas', 'asdfasd', 45456345, 'fdgsds', '2019-08-15', 'sdfsdfsfdsdfgfsd', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `apellido` varchar(40) DEFAULT NULL,
  `cedula` varchar(10) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `id_sucursal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `nombre`, `apellido`, `cedula`, `fecha_ingreso`, `id_sucursal`) VALUES
(1, 'Ernesto', 'Valverde', '98546782', '2019-06-14', 1),
(2, 'Carlos', 'Peñaranda', '1079965321', '2019-07-15', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Poblado', 'Sede principal');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `auto`
--
ALTER TABLE `auto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_sucursal` (`id_sucursal`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auto`
--
ALTER TABLE `auto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `auto`
--
ALTER TABLE `auto`
  ADD CONSTRAINT `auto_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
