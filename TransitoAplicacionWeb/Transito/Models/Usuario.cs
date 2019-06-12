using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Usuario
    {
        public Usuario()
        {
            Dictamen = new HashSet<Dictamen>();
            UsuarioBitacoraAcceso = new HashSet<UsuarioBitacoraAcceso>();
        }

        public int Idusuario { get; set; }
        public string Nombre { get; set; }
        public string ApellidoPaterno { get; set; }
        public string ApellidoMaterno { get; set; }
        public int Idcargo { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }

        public Cargo IdcargoNavigation { get; set; }
        public ICollection<Dictamen> Dictamen { get; set; }
        public ICollection<UsuarioBitacoraAcceso> UsuarioBitacoraAcceso { get; set; }
    }
}
