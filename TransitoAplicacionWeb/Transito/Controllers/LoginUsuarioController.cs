using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Transito.Models;

namespace Transito.Controllers
{
    public class LoginUsuarioController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
        public IActionResult Validar(String login, String password)
        {
            Usuario usuario = ValidarUsuario(login, password);
            bool valido = usuario != null;
            if (valido)
            {
                using (TransitoContext dbss = new TransitoContext())
                {
                    String token = "";
                    do
                    {
                        token = GenrarToken();

                    } while (dbss.UsuarioBitacoraAcceso.Any(a => a.TokenAcceso.Equals(token)));
                    UsuarioBitacoraAcceso usuariobitacora = new UsuarioBitacoraAcceso();
                    usuariobitacora.Idusuario = usuario.Idusuario;
                    usuariobitacora.TokenAcceso = token;
                    usuariobitacora.Activa = true;
                    usuariobitacora.Fecha = DateTime.Now;
                    dbss.UsuarioBitacoraAcceso.Add(usuariobitacora);
                    dbss.SaveChanges();



                }return new RedirectResult("Home/registro");
            }else


            return new RedirectResult("/");
        }

        private string GenrarToken()
        {
            Random random = new Random();
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
            return new string(Enumerable.Repeat(chars, 30)
                    .Select(s => s[random.Next(s.Length)]).ToArray());
        }

        public Usuario ValidarUsuario(String login, String password)
        {
            using (TransitoContext dbSS = new TransitoContext())
            {
                var usuario = dbSS.Usuario.FirstOrDefault(al =>
                                al.Username.Equals(login) &&
                                      al.Password.Equals(password)
                                 );
                return usuario;
            }
        }
    }
}