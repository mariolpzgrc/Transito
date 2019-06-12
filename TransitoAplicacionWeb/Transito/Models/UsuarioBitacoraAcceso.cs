using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class UsuarioBitacoraAcceso
    {
        public int Id { get; set; }
        public int? Idusuario { get; set; }
        public DateTime? Fecha { get; set; }
        public string TokenAcceso { get; set; }
        public bool? Activa { get; set; }

        public Usuario IdusuarioNavigation { get; set; }
    }
}
