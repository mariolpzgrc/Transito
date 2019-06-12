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
        //public IActionResult Validar(String login, String password)

    }
}