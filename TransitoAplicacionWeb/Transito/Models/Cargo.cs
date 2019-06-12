using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Cargo
    {
        public Cargo()
        {
            Usuario = new HashSet<Usuario>();
        }

        public int Idcargo { get; set; }
        public string NombreCargo { get; set; }

        public ICollection<Usuario> Usuario { get; set; }
    }
}
