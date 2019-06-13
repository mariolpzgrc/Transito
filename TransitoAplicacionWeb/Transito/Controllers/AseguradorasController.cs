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

    public class AseguradorasController : ControllerBase
    {
        private readonly TransitoContext _context;

        public AseguradorasController(TransitoContext context)
        {
            _context = context;
        }

        // GET: api/Aseguradoras
        [HttpGet]
        public IEnumerable<Aseguradora> GetAseguradora()
        {
            return _context.Aseguradora;
        }

        // GET: api/Aseguradoras/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetAseguradora([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var aseguradora = await _context.Aseguradora.FindAsync(id);

            if (aseguradora == null)
            {
                return NotFound();
            }

            return Ok(aseguradora);
        }

        // PUT: api/Aseguradoras/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAseguradora([FromRoute] int id, [FromBody] Aseguradora aseguradora)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != aseguradora.Idaseguradora)
            {
                return BadRequest();
            }

            _context.Entry(aseguradora).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AseguradoraExists(id))
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

        // POST: api/Aseguradoras
        [HttpPost]
        public async Task<IActionResult> PostAseguradora([FromBody] Aseguradora aseguradora)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Aseguradora.Add(aseguradora);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAseguradora", new { id = aseguradora.Idaseguradora }, aseguradora);
        }

        // DELETE: api/Aseguradoras/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAseguradora([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var aseguradora = await _context.Aseguradora.FindAsync(id);
            if (aseguradora == null)
            {
                return NotFound();
            }

            _context.Aseguradora.Remove(aseguradora);
            await _context.SaveChangesAsync();

            return Ok(aseguradora);
        }

        private bool AseguradoraExists(int id)
        {
            return _context.Aseguradora.Any(e => e.Idaseguradora == id);
        }
    }
}