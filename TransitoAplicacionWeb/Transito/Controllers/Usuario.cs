using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Transito.Models;

namespace Transito.Controllers
{
    public class Usuario : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Validar(String login, String password)
        {
            Usuario usuario = ValidarUsuario(login, password);
            bool valido = usuario != null;
            if (valido)
            {
                using (TransitoContext dbSS = new TransitoContext())
                {
                    String token = "";
                    do
                    {
                        token = GenerarToken();
                    } while (dbSS.UsuarioBitacoraAcceso.Any(a => a.TokenAcceso.Equals(token)));

                    UsuarioBitacoraAcceso registroAcceso = new UsuarioBitacoraAcceso();
                    registroAcceso.Fecha = DateTime.Now;
                    registroAcceso.Idusuario = usuario.Id;
                    registroAcceso.TokenAcceso = token;
                    registroAcceso.Activa = true;
                    dbSS.UsuarioBitacoraAcceso.Add(registroAcceso);
                    dbSS.SaveChanges();

                    byte[] arr = BitConverter.GetBytes(registroAcceso.Id);
                    HttpContext.Session.Set("SesionUsuario", arr);
                    HttpContext.Session.Set("Usuario",
                     Encoding.ASCII
                     .GetBytes($"{usuario.Nombre}"));
                    return new RedirectResult("/Administrador/Seguimiento");
                }
            }
            else
                return new RedirectResult("/");
        }

        private String GenerarToken()
        {
            Random random = new Random();
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
            return new string(Enumerable.Repeat(chars, 30)
                    .Select(s => s[random.Next(s.Length)]).ToArray());

        }
    }
}