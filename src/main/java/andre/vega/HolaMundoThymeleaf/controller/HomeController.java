package andre.vega.HolaMundoThymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    /**
     * Maneja solicitudes HTTP GET
     *
     * @param nombre
     * @param model
     * @param session
     *
     * @return el enlace del index
     */
    @GetMapping("/")
    public String index(@RequestParam(name = "nombre",
                                required = false,
                                defaultValue = "invitado") String nombre,
                        Model model, HttpSession session) {

        model.addAttribute("mensajeBienvenida", "¡Hola mundo desde springboot !");
        model.addAttribute("nombreUsuario", nombre);
        session.setAttribute("operador", "admin");
        return "index";
    }



    /**
     * Crea un saludo y color según la hora para mostrarlo en pantalla después
     *
     * @param model
     *
     * @return el enlace al HTMO de saludo
     */
    @GetMapping("/saludo")
    @ResponseBody
    public String saludo(Model model) {
        LocalTime now = LocalTime.now();
        int hora = now.getHour();
        String saludo;
        String claseCss;

        // Configura las variables según la hora
        if(hora >= 5 && hora < 12){
            saludo = "Buenos días☀️ Hora de programar⬛";
            claseCss = "color: #198754";
        }else if(hora >= 12 && hora < 19){
            saludo = "Buenas tardes⛅ Sigue así";
            claseCss = "color: #0d6efd";
        }else{
            saludo = "Buenas noches🌑 A descansar";
            claseCss = "color: #dc3545";
        }

        model.addAttribute("saludo", saludo);
        model.addAttribute("claseCss", claseCss);
        return saludo;
    }



    /**
     * Almacena un catálogo de videojuegos
     *
     * @param model
     *
     * @return enlace HTML del catálogo
     */
    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        List<Map<String, Object>> juegos = new ArrayList<>();

        juegos.add(crearJuego("The Legend of Zelda: Tears of the Kingdom", "Aventura", "Nintendo Switch", 69.99, true));
        juegos.add(crearJuego("Elden Ring", "RPG", "Multiplataforma", 59.99, true));
        juegos.add(crearJuego("Bloodborne", "Action RPG", "PlayStation 4", 19.99, false));
        juegos.add(crearJuego("Cyberpunk 2077", "RPG", "PC / Consolas", 29.99, true));

        model.addAttribute("juegos", juegos);
        return "catalogo";
    }



    /**
     * Centraliza la lógica de crear un juego
     *
     * @param titulo
     * @param genero
     * @param plataforma
     * @param precio
     * @param disponible
     *
     * @return el juego
     */
    private Map<String, Object> crearJuego(String titulo, String genero, String plataforma, double precio, boolean disponible) {
        Map<String, Object> juego = new HashMap<>();
        juego.put("titulo", titulo);
        juego.put("genero", genero);
        juego.put("plataforma", plataforma);
        juego.put("precio", precio);
        juego.put("disponible", disponible);
        return juego;
    }
}