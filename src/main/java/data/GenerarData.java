package data;

import com.github.javafaker.Faker;
import util.Utilidad;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 24/06/2024 1:58 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class GenerarData {

    private final Faker daoGeneric = new Faker(Locale.US);
    private static final Faker daoGenerico = new Faker(Locale.US);

    /**
     * Este método generara un numero random que estará entre Cero y el número introducido.
     * @param hastaNum Numero máximo hasta el cual se puede generar el numero Random.
     * @return numero Random.
     */
    public int generateRandom(int hastaNum){
        return daoGeneric.number().numberBetween(0, hastaNum + 1);
    }

    public static int generateRandomStatic(int hastaNum){
        return daoGenerico.number().numberBetween(0, hastaNum + 1);
    }

    /**
     * Este método genera un numero Random, el mismo contendrá la cantidad de dígitos especificado por el parámetro.
     * @param numDigitos Cantidad maxima de dígitos que tendrá el numero Random generado.
     * @return numero Random
     */
    public long getNumRandom(int numDigitos){
        return daoGeneric.number().randomNumber( numDigitos, true);
    }

    /**
     * Este método genera un número Random, el mismo contendrá la cantidad de dígitos especificado por el parámetro y retornara el numero random como un String.
     * @param numDigitos Cantidad maxima de dígitos que tendrá el número Random generado.
     * @return número Random.
     */
    public String getNumRandomString(int numDigitos){
        return String.valueOf( getNumRandom( numDigitos ) ) ;
    }

    /**
     * Genera de manera Random un número de telefono de RD.
     * @return número de telefono RD.
     */
    public String randomPhoneNumberRD(){
        Map<Integer,String> codigosArea = new HashMap<>();
        codigosArea.put( 0, "809");
        codigosArea.put( 1, "829");
        codigosArea.put( 2, "849");
        return codigosArea.get( generateRandom(2) ) + getNumRandomString(7);
    }

    public String getRespuestaSeguridad(String pregunta){
        Map <String, String> respuestas = new HashMap<>();
        respuestas.put("¿Cuál es la marca de su primer carro?","carro");
        respuestas.put("¿Cuál es el nombre del colegio donde cursó la primaria?","colegio");
        respuestas.put("¿Cuál es el personaje de su libro favorito?","libro");
        respuestas.put("¿Cuál es su color favorito?","color");
        respuestas.put("¿Cuál es el nombre de su abuela materna?","abuela");
        respuestas.put("¿Cuál es su hobby favorito?","hobby");
        respuestas.put("¿Cuál es el nombre de la empresa donde labora?","empresa");
        respuestas.put("¿Cuál es el nombre de la calle donde vivió en su infancia?","calle");
        respuestas.put("¿Cuál es su comida favorita?","comida");
        respuestas.put("¿Cuál es el nombre de su primera mascota?","mascota");
        respuestas.put("¿Cuál es su canción favorita?","cancion");
        respuestas.put("¿Cuál es el nombre de su artista favorito?","artista");

        return respuestas.getOrDefault(pregunta, "Esta pregunta no existe en el json");
    }

    public static String createDescripcion(){
        return " Automation IBP " + new GenerarData().getNumRandomString(5);
    }

    public String getRespuestaSeguridad(String pregunta, boolean respuestaCorrecta){
        Map <String, String> respuestas = new HashMap<>();
        String respuestasVerdaras = "";
        if (respuestaCorrecta == false){
            respuestasVerdaras = String.valueOf(  new GenerarData().getNumRandom(3) );
        }

        respuestas.put("¿Cuál es el nombre de su libro favorito?","libro" + respuestasVerdaras);
        respuestas.put("¿Cuál es su color favorito?","color" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de su abuela materna?","abuela" + respuestasVerdaras);
        respuestas.put("¿Cuál es la marca de su primer carro?","carro" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre del colegio donde cursó la primaria?","colegio" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de su primera mascota?","mascota" + respuestasVerdaras);
        respuestas.put("¿Cuál es su comida favorita?","comida" + respuestasVerdaras);
        respuestas.put("¿Cuál es su canción favorita?","cancion" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de su artista favorito?","artista" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de la calle donde vivió en su infancia?","calle" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre del hospital o clínica donde usted nació?","hospital" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de la empresa donde labora?","empresa" + respuestasVerdaras);
        respuestas.put("¿Cuál es su hobby favorito?","hobby" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de su deportista favorito?","deportista" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre del lugar que sueña visitar?","lugar" + respuestasVerdaras);
        respuestas.put("¿Cuál fue su materia favorita en el bachillerato?","materia" + respuestasVerdaras);
        respuestas.put("¿Cuál es la fecha del aniversario de su boda?","boda" + respuestasVerdaras);
        respuestas.put("¿Cuál es la fecha de nacimiento de su primer hijo?","hijo" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre del centro de belleza que visita?","belleza" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de su restaurant favorito?","restaurant" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de la universidad donde estudia/estudió?","universidad" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de su tienda favorita?","tienda" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de su pelicula favorita?","pelicula" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de la Iglesia que frecuenta?","iglesia" + respuestasVerdaras);
        respuestas.put("¿Cuál es el personaje de su libro favorito?","libro" + respuestasVerdaras);
        respuestas.put("¿Cuál fue el primer trabajo que realizaste?","trabajo" + respuestasVerdaras);
        respuestas.put("¿Cuál es el apellido del profesor de tu colegio que menos te agradaba?","apellido" + respuestasVerdaras);
        respuestas.put("¿En qué ciudad te casaste?","ciudad" + respuestasVerdaras);
        respuestas.put("¿Quién es tu personaje histórico favorito?","personaje" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de la chica o chico con quien te diste tu primer beso?","beso" + respuestasVerdaras);
        respuestas.put("¿En qué ciudad viviste la mayor parte de tu infancia?","ciudad" + respuestasVerdaras);
        respuestas.put("¿Cuál era tu apodo cuando niño/niña?","apodo" + respuestasVerdaras);
        respuestas.put("¿Cuál es el nombre de tu profesor favorito en el colegio?","profesor" + respuestasVerdaras);
        respuestas.put("¿Cuál era tu actividad preferida en la escuela?","escuela" + respuestasVerdaras);

        return respuestas.getOrDefault(pregunta, "Esta pregunta no existe en el json");
    }


    private static final String[] NOMBRES_MASCULINOS = {
            "Alejandro", "Alberto", "Andrés", "Antonio", "Benjamín", "Carlos", "Cristian", "Daniel",
            "David", "Diego", "Eduardo", "Esteban", "Fernando", "Francisco", "Gabriel",
            "Gonzalo", "Hugo", "Ignacio", "Javier", "Joaquín", "José", "Julio", "Leonardo",
            "Luis", "Manuel", "Marcos", "Mario", "Martín", "Miguel", "Nicolás", "Pablo",
            "Pedro", "Rafael", "Ricardo", "Roberto", "Santiago", "Sergio", "Vicente", "Adrián",
            "Alejo", "Arnaldo", "Braulio", "César", "Dante", "Emilio", "Felipe", "Germán",
            "Héctor", "Ismael", "Julián", "Klaus", "Lorenzo", "Mauricio", "Néstor", "Óscar",
            "Rubén", "Salvador", "Salomón", "Teodoro", "Valentín", "Walter", "Yahir", "Zacarías",
            "Agustín", "Ariel", "Bruno", "Claudio", "Cristóbal", "Damián", "Ezequiel",
            "Gustavo", "Hugo",  "Leonel",  "Octavio", "Rodolfo", "Simón",
            "Tobías", "Ulises",  "Wilfredo", "Xavier", "Zoe", "Abel", "Bertín",
            "Cipriano", "Dario", "Elías",  "Gael", "Horacio", "Iván", "Jairo",
            "Kiko", "Lázaro", "Manuel", "Nicanor", "Otto", "Panchito", "Quirino", "Ramiro",
              "Vidal", "Yves", "Alfredo", "Braulio", "Cesar",
            "Dorian", "Eliseo",  "Galo",  "Jorge", "Kiko",
            "Leopoldo", "Marcelino",  "Ovidio", "Petrus", "Renato", "Sergio",
            "Victor", "Waldo", "Ximeno", "Yago",  "Alonso", "Benito",
            "Evaristo", "Francisco", "Gregorio", "Hugo", "Isidoro", "Jovito", "Leandro",
            "Oswaldo",  "Raúl", "Santos", "Tiburcio",  "Victoriano", "Yamil",
            "Zenón", "Aníbal", "Basilio",  "Dimas", "Edmundo", "Fidel",
            "Hugo",   "Misael", "Nereo", "Pánfilo", "Rufino",
            "Simeón",   "Bernardo", "Ciro", "Diego",
            "Eusebio", "Florencio",  "Humberto", "Ildefonso",
            "Marcelino", "Misael",  "Otoniel",  "Raúl", "Santos", "Tiburcio",
             "Victoriano", "Yamil",  "Adriano", "Alejo", "Bardo", "Clemente",
            "Dario", "Ernesto",      "León",
            "Moisés",  "Otto",  "Ramón", "Saúl",
             "Wilfredo", "Ximeno", "Yasir",  "Abraham", "Bruno",
                "Hugo",   "Lázaro",
            "Marcelo",  "Óscar",  "Renato", "Silvano", "Tomás", "Urbano",
             "Waldo",   "Alfonso", "Bartolo",  "Daniel",
               "Hugo",   "Luis", "Mariano",
             "Oscar", "Raúl",   "Ubaldo",  "Wilmer",
            "Xavier",   "Aladino", "Benedicto", "Calixto", "Dámaso", "Eladio",
            "Fabian", "Gonzalo", "Hilario", "Jovanny", "León", "Marino", "Narciso", "Octavio",
            "Prudencio",  "Silvestre", "Teófilo", "Ursulo", "Ventura", "Wenceslao",
            "Xisto", "Yomero", "Zoran", "Alcides", "Balbino", "Clemente", "Demetrio", "Ernesto",
            "Federico",  "Hugo",    "Matías",
            "Omar",  "Ricardo", "Santiago",   "Wilmer", "Xavi",
               "Benjamín",  "Dámaso",
              "Julio", "Leonardo",
               "Víctor", "Yago"
    };

    private static final String[] NOMBRES_FEMENINO = {
            "Adriana", "Alejandra", "Alicia", "Amalia", "Ana", "Beatriz", "Belinda", "Berta",
            "Blanca", "Camila", "Carla", "Carmen", "Cecilia", "Claudia", "Clara", "Cristina",
            "Daniela", "Diana", "Elena", "Elisa", "Elsa", "Emilia", "Erika", "Estefanía",
            "Estrella", "Eva", "Fabiola", "Fernanda", "Flor", "Florencia", "Francisca", "Gabriela",
            "Gloria", "Graciela", "Guadalupe", "Hilda", "Imelda", "Inés", "Isabel", "Jacqueline",
            "Jimena", "Josefina", "Juana", "Juliana", "Karina", "Laura", "Liliana", "Lourdes",
            "Lucía", "Magdalena", "Maira", "Marta", "María", "Marisol", "Mónica", "Natalia",
            "Olivia", "Pilar", "Raquel", "Renata", "Rita", "Rocío", "Salvadora", "Samantha",
            "Sara", "Silvia", "Sofía", "Teresa", "Valentina", "Vanessa", "Verónica", "Victoria",
            "Yolanda", "Zulema", "Adela", "Aida", "Alba", "Alondra", "Aurelia",
            "Bianca", "Brianda", "Carmen", "Cinthia", "Dalia", "Delia",  "Dulce",
            "Evelyn", "Estela", "Fabiola", "Felicia", "Florencia", "Gabriela", "Gina", "Gladis",
            "Graciela", "Guadalupe", "Iliana", "Irene", "Ivette", "Janet", "Jazmín",
            "Joana", "Josefa", "Karina", "Leila", "Leticia", "Luciana", "Mabel", "María",
            "Magdalena", "Mariana", "Mireya", "Nadia", "Nancy", "Nerea", "Nina", "Norita",
            "Olga", "Paloma", "Paola", "Raquel", "Rebeca", "Renée", "Rosalía", "Sonia",
            "Soraya", "Susana", "Tamara", "Teresa", "Ursula", "Vanesa", "Viviana", "Yasmin",
            "Ylenia", "Zaira", "Zuleika", "Ángeles", "Bárbara",  "Denise",
            "Eugenia", "Fatima", "Genoveva", "Heloísa", "Isidora", "Johana", "Karla", "Leandro",
            "Miriam", "Nora",  "Pilar", "Quetzal", "Rafaela", "Salma", "Tatiana",
            "Ulda", "Vera", "Wilma", "Xiomara", "Yaneli", "Zina", "Aitana", "Amparo",
            "Azucena", "Belén", "Carmela",  "Delia", "Esperanza", "Fanny", "Griselda",
             "Ivana", "Jasmin", "Kassandra", "Leticia", "Mafalda", "Milagros", "Natalia",
            "Olga", "Priscila", "Quirina", "Rosario", "Silvana", "Tania", "Ursula", "Veronica",
            "Wendy", "Ximena", "Yareli", "Zoe", "Alma", "Blanca", "Carmelita", "Daisy",
            "Estrella", "Fiorella", "Ginebra", "Helena", "Ilse", "Jocelyn",  "Lorenza",
            "Maité", "Nadia", "Otilia", "Petra", "Querida",  "Sabrina", "Tania",
            "Yaneli", "Zoe", "Ailín",   "Dámaris", "Edith", "Frida",
            "Giselle", "Ingrid", "Jimena",  "Luján", "Margarita", "Nerina",
            "Ofelia", "Paulina", "Quiana", "Reina", "Salomé", "Tatiana", "Uriela", "Violeta",
            "Wendy", "Xiomara", "Yasmina", "Zinnia", "Adelina", "Ainara", "Alma", "Ariadna",
            "Aurora",  "Candelaria", "Candela", "Dania", "Elvira", "Felicidad", "Guida",
            "Jazmín", "Leocadia", "María del Mar", "Nuria",  "Patricia",  "Silvia",
            "Teresita", "Ursulina", "Virginia", "Yasmina", "Zulema", "Adela", "Araceli", "Berenice",
            "Cynthia",  "Estefanía", "Florinda", "Gloria", "Inocencia", "Jaquelina", "Keren",
            "Lucila", "Milena", "Nayeli", "Oriana", "Paloma", "Quinna",  "Salvadora",
            "Teodora", "Veridiana", "Yolanda", "Zaira", "Ailín",   "Dámaris",
            "Edith", "Frida", "Giselle", "Ingrid", "Jocelyn",  "Luján", "Margarita"
    };

    private static final String[] APELLIDOS = {
            "García", "Martínez", "López", "Hernández", "González", "Pérez", "Sánchez", "Ramírez",
            "Torres", "Flores", "Gómez", "Díaz", "Vázquez", "Castillo", "Mendoza", "Morales",
            "Ríos", "Reyes", "Cruz", "Ortiz", "Serrano", "Salazar", "Ponce", "Núñez",
            "Cabrera", "Jiménez", "Vega", "Cortez", "Rojas", "Camarillo", "Pacheco", "Lara",
            "Bermúdez", "Valdez", "Medina", "Soto", "Aguilar", "Cisneros", "Duarte", "Alvarez",
            "Salas", "Cervantes", "Maldonado", "Delgado", "Márquez", "Marín", "Aguirre", "Bautista",
            "Navarro", "Tellez", "Rangel", "Pineda", "Montoya", "Acosta", "Salinas", "Carrillo",
            "Pérez", "León", "Salazar", "Mora", "Sánchez", "Carranza", "Gonzales", "Quezada",
            "Ríos", "Bañuelos", "Coronado", "Figueroa", "Angulo", "Zúñiga", "Alvarado", "Gálvez",
            "Samaniego", "Arce", "Bermúdez", "Gómez", "Velázquez", "Rivas", "Mora", "Lozano",
            "Benavides", "Serrano", "Pizarro", "Peña", "Cordero", "Cáceres", "Bautista", "Quintero",
            "Salazar", "Rosales", "Aguayo", "Pantoja", "Castañeda", "Galván", "Ríos", "Salcedo",
            "Alfaro", "Cáceres", "Silva", "López", "González", "Cruz", "Moreno", "Ocampo",
            "Calderón", "Aguirre", "Franco", "Aguilera", "González", "Alonzo", "Alba", "Núñez",
            "Solís", "Ramos", "Martel", "Olivares", "Villarreal", "Guerrero", "Gonzales", "Quezada",
            "Mora", "Barbosa", "López", "Hernández", "Bolaños", "Tovar", "Aldana", "Valladares",
            "Rosales", "Cruz", "Cárdenas", "Alvarez", "Benítez", "González", "Arriaga", "Juárez",
            "Pineda", "Rivas", "Medina", "Estrada", "Ramírez", "Pérez", "Fuentes", "Bermúdez",
            "Sandoval", "Reyes", "Salazar", "Ramírez", "Núñez", "Sosa", "Torres", "Gutiérrez",
            "Sáenz", "Palacios", "Varela", "Zamora", "Cordero", "Rojas", "Pérez", "Ruiz",
            "Ibarra", "Suárez", "Martínez", "Gonzalez", "Cordero", "Estrada", "Cabrera", "Nieves",
            "Torres", "Bañuelos", "Feliciano", "Sánchez", "Arrieta", "Ortega", "Valdez", "Vargas",
            "Barrios", "Fuentes", "González", "Solís", "Duarte", "Montes", "Salas", "Hinojosa",
            "Burgos", "Alarcón", "Ahumada", "Figueroa", "Gálvez", "Naranjo", "Medina", "Yáñez",
            "Cano", "Ríos", "Vera", "López", "Maldonado", "Salazar", "Pérez", "Meza",
            "Barrientos", "Cardenas", "Pérez", "Carmona", "Sánchez", "Zaragoza", "Cano", "Ochoa",
            "Bermúdez", "Escobar", "Moreno", "Aguirre", "Calderón", "Ruiz", "Silva", "Hernández",
            "Ayala", "Ramírez", "Mora", "Reyes", "Rosales", "Vásquez", "Aguilar", "Marquez",
            "Juárez", "Mendoza", "Fuentes", "Ramírez", "Beltrán", "Montoya", "Tello", "Aldana",
            "Báez", "Bermúdez", "Mejía", "Delgado", "González", "Ramírez", "Navarrete", "Reyes",
            "López", "Alvarado", "Pérez", "Sierra", "Vargas", "Salazar", "Aguilera", "Aguayo",
            "Torres", "Mora", "Córdova", "Pineda", "Serrano", "Reyes", "González", "Valenzuela",
            "Jaramillo", "Carbajal", "Carrillo", "Camarillo", "Zúñiga", "Orozco", "Santiago",
            "Pérez", "Carranza", "Lopez", "Maldonado", "Cortez", "Velasquez", "Fuentes", "Peña",
            "Pérez", "Vazquez", "Acosta", "Díaz", "Vargas", "Bravo", "Navarro", "Cisneros",
            "Márquez", "Cortez", "López", "Alcántara", "Benavides", "Cordero", "Carmona", "Figueroa",
            "Samaniego", "Rivas", "Velasco", "Valle", "Gonzalez", "Torres", "Zamora", "López"
    };

    public static String getNombreFemenino(){
        return NOMBRES_FEMENINO[generateRandomStatic(NOMBRES_FEMENINO.length)];
    }

    public static String getNombresFemeninos(){
        return getNombreFemenino() + " " + getNombreFemenino();
    }

    public static String getNombreMasculino(){
        return NOMBRES_MASCULINOS[generateRandomStatic(NOMBRES_MASCULINOS.length)];
    }

    public static String getNombresMasculinos(){
        return getNombreMasculino() + " " + getNombreMasculino();
    }

    public static String getApellido(){
        return APELLIDOS[generateRandomStatic(APELLIDOS.length)];
    }

    public static String getApellidos(){
        return getApellido() + " " + getApellido();
    }

    public static String getNombreCompletoMasculino(){
        return getNombresMasculinos() + " " + getApellidos();
    }

    public static String getNombreCompletoFemenino(){
        return getNombresFemeninos() + " " + getApellidos();
    }

}
