using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

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
            return null;
        }
    }
}