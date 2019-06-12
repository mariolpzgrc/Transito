using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Transito.Models;

namespace Transito.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LoginConductorController : ControllerBase
    {
        private readonly TransitoContext _context;

        public LoginConductorController(TransitoContext context)
        {
            _context = context;
        }

        //POST: api/Login
        [HttpPost]


    }
}