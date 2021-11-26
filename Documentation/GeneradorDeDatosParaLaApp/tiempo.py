"""
El tiempo a mi gusto
"""
# -⁻- coding: UTF-8 -*-
from datetime import date
from os import path
from random import randint
import math
import time

class Tiempo:
    def __init__(self):
        # Obj para extraer la información del tiempo
        self.tiempo = date.today()
        # Para Obtener los dias de la semana
        self.diasDeLaSemana = ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo']
        self.meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
        # Para saber que mes tiene 31 y 31 dias, feb no le voy a contar el bisciesto
        self.duracionMeses = [31,28,31,30,31,30,31,31,30,31,30,31]

    def año(self):
        """Retorna en que a+o estamios"""
        return self.tiempo.year

    def mes(self):
        """Retorna en que mes estamos"""
        return self.tiempo.month

    def diaNumero(self):
        """Retorna en que dia estamos el nro de dia"""
        return self.tiempo.day

    def diaDeLaSemana(self):
        """Retorna que dia es lunes o martes..."""
        return self.diasDeLaSemana[self.tiempo.weekday()]

    def nombreMes(self, mes):
        """
        Retorna el nombre del mes 0-> enero 1->febrero
        """
        return self.meses[mes]

    def diasDeMes(self, mes):
        """
        Retorna de los meses del 0 -> Enero 11 -> Diciembre
        cuantos dias tienen dicho mes
        """
        return self.duracionMeses[mes]

    def hora(self):
        """
        Esto retorna la hora
        """
        return str(time.ctime())

    def incrementarFechaXDias(self, año, mes, dia, incremento):
        """Retorna la fecha despues del incremento de dias.
        Nota: no es tan exacto... aveces falla por dos o tres dias
        """

        nuevoAño = int(año)
        nuevoMes = int(mes)
        nuevoDia = int(dia)

        # Si no hay que incrementar dias
        if(incremento == 0):
            return str(nuevoAño) +":"+str(self.meses[int(nuevoMes)-1])+":"+str(nuevoDia)

        # calcular si es más de un año
        if(incremento>=365):
            if(incremento == 365):
                return str(int(nuevoAño)+1) +":"+str(self.meses[int(nuevoMes)-1])+":"+str(nuevoDia)
            else:
                #Recalcular año y volver a llamar
                aumentoAños = math.floor(incremento / 365)
                nuevoIncremento = incremento%365
                return self.incrementarFechaXDias(int(nuevoAño)+aumentoAños, int(nuevoMes), int(nuevoDia), nuevoIncremento)


        # Calcular cuatos dias falta para que termine el año
        diasFaltantes = self.cuantosDiasFaltanParaTerminarElAñoFechaX(int(mes), int(dia))
        if diasFaltantes < incremento:
            # Poner la fecha en 1 ro de enero del siguiente año y recalcular
            return self.incrementarFechaXDias(int(nuevoAño)+1, 1, 1, incremento-diasFaltantes)

        
        # Comienza a incrementar los dias
        contador = 1
        temporalDiasMesActual = nuevoDia
        while incremento > 0:

            if contador + temporalDiasMesActual > self.duracionMeses[int(nuevoMes)-1]:
                temporalDiasMesActual = 0
                nuevoDia = 1
                contador = 1
                nuevoMes = int(nuevoMes) + 1

            nuevoDia = int(nuevoDia) + 1

            contador = contador + 1
            incremento = incremento - 1
        

        return str(nuevoAño)+":"+str(nuevoMes)+":"+str(nuevoDia)

    def cuantosDiasFaltanParaTerminarElAño(self):
        mesActual = self.mes()
        diaActual = self.diaNumero()

        cuantosDiashanPasado = 0
        #Cuantos Dias han pasado
        for i in range(0, mesActual-1):
            cuantosDiashanPasado = cuantosDiashanPasado + self.duracionMeses[i]

        #Le sumo los dias actuales
        cuantosDiashanPasado = cuantosDiashanPasado + diaActual
           
        return 365 - cuantosDiashanPasado

    def cuantosDiasFaltanParaTerminarElAñoFechaX(self, mes, dia):
        """
        enero = 1
        """
        mesActual = mes
        diaActual = dia

        cuantosDiashanPasado = 0
        #Cuantos Dias han pasado
        for i in range(0, mesActual-1):
            cuantosDiashanPasado = cuantosDiashanPasado + self.duracionMeses[i]

        #Le sumo los dias actuales
        cuantosDiashanPasado = cuantosDiashanPasado + diaActual
           
        return 365 - cuantosDiashanPasado



    def estampaDeTiempo(self):
        """
        Este metodo retorna la firma de mi tiempo:
        Año NumeroMes NumeroDia
        """
        return str(self.año()) + " " + str(self.mes()) + " " + str(self.diaNumero())