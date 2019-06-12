using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Dictamen
    {
        public Dictamen()
        {
            Reporte = new HashSet<Reporte>();
        }

        public int Folio { get; set; }
        public string Descripcion { get; set; }
        public DateTime FechaDictamen { get; set; }
        public string Estado { get; set; }
        public int Idusuario { get; set; }

        public Usuario IdusuarioNavigation { get; set; }
        public ICollection<Reporte> Reporte { get; set; }
    }
}
