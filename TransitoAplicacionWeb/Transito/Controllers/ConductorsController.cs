using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Transito.Models;

namespace Transito.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ConductorsController : ControllerBase
    {
        private readonly TransitoContext _context;

        public ConductorsController(TransitoContext context)
        {
            _context = context;
        }

        // GET: api/Conductors
        [HttpGet]
        public IEnumerable<Conductor> GetConductor()
        {
            return _context.Conductor;
        }

        // GET: api/Conductors/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetConductor([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var conductor = await _context.Conductor.FindAsync(id);

            if (conductor == null)
            {
                return NotFound();
            }

            return Ok(conductor);
        }

        // PUT: api/Conductors/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutConductor([FromRoute] int id, [FromBody] Conductor conductor)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != conductor.Idconductor)
            {
                return BadRequest();
            }

            _context.Entry(conductor).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ConductorExists(id))
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

        // POST: api/Conductors
        [HttpPost]
        public async Task<IActionResult> PostConductor([FromBody] Conductor conductor)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Conductor.Add(conductor);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetConductor", new { id = conductor.Idconductor }, conductor);
        }

        //POST: api/Conductors/Login
        [HttpPost("Login")]
        public async Task<IActionResult> Login([FromBody] dynamic credentials)
        {
            var telefono = (string)credentials["telefono"];
            var password = (string)credentials["password"];
            var conductor = await _context.Conductor.SingleOrDefaultAsync(c => c.Telefono == telefono && c.Password == password);

            if (conductor == null)
            {
                return NotFound();
            }

            return Ok(conductor);
        }


        // DELETE: api/Conductors/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteConductor([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var conductor = await _context.Conductor.FindAsync(id);
            if (conductor == null)
            {
                return NotFound();
            }

            _context.Conductor.Remove(conductor);
            await _context.SaveChangesAsync();

            return Ok(conductor);
        }

        private bool ConductorExists(int id)
        {
            return _context.Conductor.Any(e => e.Idconductor == id);
        }
    }
}