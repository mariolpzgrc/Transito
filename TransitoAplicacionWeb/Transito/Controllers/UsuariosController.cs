using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Transito.Models;
using Microsoft.AspNetCore.Cors;

namespace Transito.Controllers
{
    [Route("api/[controller]")]
    [ApiController]

    public class UsuariosController : ControllerBase
        {
            
            private readonly TransitoContext _context;

            public UsuariosController(TransitoContext context)
            {
                _context = context;
            }

            // GET: api/Usuarios
            [HttpGet]
            public IEnumerable<Usuario> GetUsuario()
            {
                return _context.Usuario;
            }

            // GET: api/Usuarios/5
            [HttpGet("{id}")]
            public async Task<IActionResult> GetUsuario([FromRoute] int id)
            {
                if (!ModelState.IsValid)
                {
                    return BadRequest(ModelState);
                }

                var usuario = await _context.Usuario.FindAsync(id);

                if (usuario == null)
                {
                    return NotFound();
                }

                return Ok(usuario);
            }

            // PUT: api/Usuarios/5
            [HttpPut("{id}")]
            public async Task<IActionResult> PutUsuario([FromRoute] int id, [FromBody] Usuario usuario)
            {
                if (!ModelState.IsValid)
                {
                    return BadRequest(ModelState);
                }

                if (id != usuario.Idusuario)
                {
                    return BadRequest();
                }

                _context.Entry(usuario).State = EntityState.Modified;

                try
                {
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!UsuarioExists(id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }

                return NoContent();
            }
        public void Registro(String username,
        String paterno, String materno, String nombre,
        int cargo, String password)
        {
            

                Usuario usuario = new Usuario();
                usuario.ApellidoPaterno = paterno;
                usuario.Idcargo  = cargo;
                usuario.ApellidoMaterno = materno;
                usuario.Username = username;
                usuario.Nombre = nombre;
                usuario.Password = password;
               
                PostUsuario(usuario);

            
        }
        // POST: api/Usuarios
        [HttpPost]
            public async Task<IActionResult> PostUsuario([FromBody] Usuario usuario)
            {
                if (!ModelState.IsValid)
                {
                    return BadRequest(ModelState);
                }

                _context.Usuario.Add(usuario);
                await _context.SaveChangesAsync();

                return CreatedAtAction("GetUsuario", new { id = usuario.Idusuario }, usuario);
            }

            // DELETE: api/Usuarios/5
            [HttpDelete("{id}")]
            public async Task<IActionResult> DeleteUsuario([FromRoute] int id)
            {
                if (!ModelState.IsValid)
                {
                    return BadRequest(ModelState);
                }

                var usuario = await _context.Usuario.FindAsync(id);
                if (usuario == null)
                {
                    return NotFound();
                }

                _context.Usuario.Remove(usuario);
                await _context.SaveChangesAsync();

                return Ok(usuario);
            }

            private bool UsuarioExists(int id)
            {
                return _context.Usuario.Any(e => e.Idusuario == id);
            }
        }


        //public IActionResult Validar(String login, String password)

    }
