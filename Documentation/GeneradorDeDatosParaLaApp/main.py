"""
Esto es el genrador de datos para la APP android del diario.

Se procede a actuar de 2 modos: con datos ramdom
                                con los datos de la carpeta Datos

y procede a generar  SQL.txt

100 registros random
todos los registros reales

option 1 : generate ramdon data of time inversion
option 2 : generate ramdon data of time inversion


"""
from tkinter import * # para graficar
import os # Libreria para acceder al disco duro y carpetas
from os import scandir # Para listar archivos de carpetas
import time # Para ver el tiempo de creacion de los archivos
from tiempo import *  # Para el manejo de fechas

import random # Para datos aleatoreos


class Software:
    def __init__(self) -> None:
        self.rutaDelProyecto = str(os.path.dirname(os.path.abspath(__file__))) # En donde estoy padado
        self.tiempo = Tiempo()
        self.pantalla = Tk()
        self.tela = Canvas(self.pantalla, height=480, width=720, bg="snow")
        self.lblPrograma = Label(self.tela, text="Generador de datos para la base de datos de la APP")
        self.lblGenerarDatosDeInversionTiempo = Label(self.tela, text="Generar Datos inversión de tiempo")
        self.btnGenerateDatosInversionTiempoRandom = Button(self.tela, text="Random", command= lambda : self.generateRandomData(1))
        self.btnGenerateDatosInversionTiempoReal = Button(self.tela, text="Real", command= lambda : self.generateRandomData(2))
        self.lblGenerarDatosInversionEconomica = Label(self.tela, text="Generar Datos inversión economica")
        self.btnGenerarDatosInversionEconomicaRandon =  Button(self.tela, text="Random", command= lambda : self.generateRandomData(3))
        self.btnGenerarDatosInversionEconomicaReal =  Button(self.tela, text="Real", command= lambda : self.generateRandomData(4))
        self.lblGenerarDatosDeSentimientos = Label(self.tela, text="Generar Datos De Sentimientos")
        self.btnGenerarDatosDeSentimientosRandon =  Button(self.tela, text="Random", command= lambda : self.generateRandomData(5))
        self.btnGenerarDatosDeSentimientoscaReal =  Button(self.tela, text="Real", command= lambda : self.generateRandomData(6))


        #Mostrar vista
        self.renderizar()


    def renderizar(self):
        self.pantalla.title("LifeRegisterDiary Data generator v1.0")
        self.pantalla.geometry("720x480")

        self.tela.place(x=0, y=0)
        self.lblPrograma.place(x=20, y=20)
        self.lblGenerarDatosDeInversionTiempo.place(x=20, y=80)
        self.btnGenerateDatosInversionTiempoRandom.place(x=90, y=110)
        self.btnGenerateDatosInversionTiempoReal.place(x=100, y=140)

        self.lblGenerarDatosInversionEconomica.place(x=240, y=80)
        self.btnGenerarDatosInversionEconomicaRandon.place(x=280, y=110)
        self.btnGenerarDatosInversionEconomicaReal.place(x=290, y=140)

        self.lblGenerarDatosDeSentimientos.place(x=480, y=80)
        self.btnGenerarDatosDeSentimientosRandon.place(x=520, y=110)
        self.btnGenerarDatosDeSentimientoscaReal.place(x=530, y=140)


        self.pantalla.mainloop()


    def generateRandomData(self, option):
        if option == 1:
            self.saveInTXT(self.generarDatosRandomDeDistribucionDeTiempo())
        if option == 2:
            self.saveInTXT(self.generarDatosRealDeDistribucionDeTiempo())
        if option == 3:
            pass
        if option == 4:
            self.saveInTXT(self.generarDatosRealDeDistribucionEconomica())
        if option == 5:
            pass
        if option == 6:
            self.saveInTXT(self.generarDatosRealDeSentimientos())
            

        

            

    def generateRealData(self):
        pass


    def poppup(self, text):
        t = Toplevel()
        t.geometry("300x300")
        lbl = Label(t, text=text)
        lbl.place(x=10, y=10)


    def getHourFormat(self, h):
        ampm = "am"
        hour = 0

        # Put am pm
        if(h>6 and h<19):
            ampm = "pm"

        # put hour
        if(h<=6):
            hour = 6 + h
        elif (h>6 and h<19):
            hour = h - 6
        else:
            hour = h - 18

        return str(hour) + ":" + ampm


    def saveInTXT(self, data):
        try:
            path = self.rutaDelProyecto + "\\SQL.txt" 
            f = open(path, "w", encoding="UTF-8")
            f.write(data)
            f.close()
        except:
            pass

    def generarDatosRandomDeDistribucionDeTiempo(self):
            try:
                path = self.rutaDelProyecto+"\\Datos\\actividades.txt"
                estadosEmocionalesFile = open(path, "r", encoding="UTF-8")
                txtEstadosEmociolaes = estadosEmocionalesFile.read().split("\n")
                timeStampAño = str(self.tiempo.año())
                timeStampMes = str(self.tiempo.mes())
                timeStampDia = str(self.tiempo.diaNumero())
                SQL = ""
                #GenerateSqlOutPut
                for i in range(0, 100):
                    SQLHead = "INSERT INTO t_day_time_distribution (timeStamp, hour, activity) VALUES ("
                    # Generate data all day
                    for h in range(0, 24):
                        try:
                            newTimeStamp = self.tiempo.incrementarFechaXDias(timeStampAño, timeStampMes, timeStampDia, i)
                        except:
                            print("Error en", i, timeStampAño, timeStampMes, timeStampDia)
                        activity = txtEstadosEmociolaes[random.randint(0, len(txtEstadosEmociolaes)-1)]
                        newSQL =  SQLHead + "\'"+newTimeStamp+"\', "+"\'"+self.getHourFormat(h)+"\', "+"\'"+activity+"\');\n" 
                        SQL = SQL + newSQL

                return SQL
            except:
                self.poppup("Error no se puede generar los datos")
                return ""


    def generarDatosRealDeDistribucionDeTiempo(self):
        try:
            nombreArchivos = []
            ruta = self.rutaDelProyecto+"\\Datos\\t_day_time_distribution"

            # Load all files in folder
            for i in scandir(ruta):
                if i.is_file():
                    nombreArchivos.append(i.name)

            # Generate SQL 
            SQLHead = "INSERT INTO t_day_time_distribution (timeStamp, hour, activity) VALUES ("
            SQL = ""

            for i in nombreArchivos:
                try:
                    time = str(i).split(" ")
                    timeStampAño = str(time[0])
                    timeStampMes = self.tiempo.meses[int(time[1])-1]
                    timeStampDia = str(time[2].split(".")[0])
                    #  Cacth time Stamp
                    timeStamp = str(timeStampAño)+":"+str(timeStampMes)+":"+str(timeStampDia)
                    
                    data = open(ruta+"\\"+i, "r", encoding="UTF-8").read()
                    # Cacth hour and activity
                    for j in data.split("\n"):
                        if str(j).strip() != "":
                            hour = j.split(":")[0]
                            activity = j.split(":")[1]
                            newSQL = SQLHead + "\'"+timeStamp+"\', "+"\'"+hour+"\', \'"+activity+"\');\n"

                            SQL = SQL + newSQL

                except:
                    print("Error", i)

            return SQL
        except:
            self.poppup("Error no se puede generar los datos")
            return ""








    def generarDatosRealDeDistribucionEconomica(self):
        try:
            nombreArchivos = []
            ruta = self.rutaDelProyecto+"\\Datos\\t_economy_t_account"

            # Load all files in folder
            for i in scandir(ruta):
                if i.is_file():
                    nombreArchivos.append(i.name)

            # Generate SQL 
            SQLHead = "INSERT INTO t_economy_t_account (timeStamp, id, concept, debit, credit) VALUES ("
            SQL = ""

            for i in nombreArchivos:
                try:
                    #Get time stamp
                    rutaArchivo = ruta+"\\"+i
                    date = str(time.ctime(os.path.getmtime(rutaArchivo)))

                    timeStampYear = date.split(" ")[-1]
                    timeStampMes = str(i).split(" ")[0]
                    timeStampDay = str(str(i).split(" ")[-1]).split(".")[0]
                    timeStamp = str(timeStampYear)+":"+str(timeStampMes)+":"+str(timeStampDay)

                    id = 0
                    data =  open(ruta+"\\"+i, "r", encoding="UTF-8").read()
                    for j in data.split("\n"):
                        if str(j).strip() != "":
                            concept = j.split(";")[0]
                            debit = j.split(";")[1]
                            credit = j.split(";")[2]
                            newSQL = SQLHead + "\'"+timeStamp+"\', "+str(id)+",\'"+concept+"\', "+debit+", "+str(credit)+");\n"
                            id = id + 1
                            
                            SQL = SQL + newSQL
                    
                except:
                    print("Error", i)

            return SQL
        except:
            self.poppup("Error no se puede generar los datos")
            return ""


    def generarDatosRealDeSentimientos(self):
        try:
            nombreArchivos = []
            ruta = self.rutaDelProyecto+"\\Datos\\t_personal_feeling_counter"

            # Load all files in folder
            for i in scandir(ruta):
                if i.is_file():
                    nombreArchivos.append(i.name)

            # Generate SQL 
            SQLHead = "INSERT INTO t_personal_feeling_counter (timeStamp, feelingName) VALUES ("
            SQL = ""

            for i in nombreArchivos:
                try:
                    #Get time stamp
                    timeStampYear = str(i).split(" ")[0]
                    timeStampMes = self.tiempo.meses[int(str(i).split(" ")[1])-1]
                    timeStampDay = str(str(i).split(" ")[2]).split(".")[0]
                    timeStamp = str(timeStampYear)+":"+str(timeStampMes)+":"+str(timeStampDay)

                    data =  open(ruta+"\\"+i, "r", encoding="UTF-8").read()

                    newSQL = SQLHead + "\'"+timeStamp+"\', \'"+data+"\');\n"

                    SQL = SQL + newSQL
                    
                except:
                    print("Error", i)

            return SQL
        except:
            self.poppup("Error no se puede generar los datos")
            return ""
    
        

s = Software()
