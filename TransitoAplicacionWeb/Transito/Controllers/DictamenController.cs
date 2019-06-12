using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Transito.Models;

namespace Transito.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DictamenController : ControllerBase
    {
        private readonly TransitoContext _context;

        public DictamenController(TransitoContext context)
        {
            _context = context;
        }

        // GET: api/Dictamen
        [HttpGet]
        public IEnumerable<Dictamen> GetDictamen()
        {
            return _context.Dictamen;
        }

        // GET: api/Dictamen/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetDictamen([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var dictamen = await _context.Dictamen.FindAsync(id);

            if (dictamen == null)
            {
                return NotFound();
            }

            return Ok(dictamen);
        }

        // PUT: api/Dictamen/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutDictamen([FromRoute] int id, [FromBody] Dictamen dictamen)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != dictamen.Folio)
            {
                return BadRequest();
            }

            _context.Entry(dictamen).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DictamenExists(id))
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

        // POST: api/Dictamen
        [HttpPost]
        public async Task<IActionResult> PostDictamen([FromBody] Dictamen dictamen)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Dictamen.Add(dictamen);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (DictamenExists(dictamen.Folio))
                {
                    return new StatusCodeResult(StatusCodes.Status409Conflict);
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetDictamen", new { id = dictamen.Folio }, dictamen);
        }

        // DELETE: api/Dictamen/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDictamen([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var dictamen = await _context.Dictamen.FindAsync(id);
            if (dictamen == null)
            {
                return NotFound();
            }

            _context.Dictamen.Remove(dictamen);
            await _context.SaveChangesAsync();

            return Ok(dictamen);
        }

        private bool DictamenExists(int id)
        {
            return _context.Dictamen.Any(e => e.Folio == id);
        }
    }
}