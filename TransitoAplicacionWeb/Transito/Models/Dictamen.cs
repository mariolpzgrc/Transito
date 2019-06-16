using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Dictamen
    {
        public int Folio { get; set; }
        public string Descripcion { get; set; }
        public DateTime FechaDictamen { get; set; }
        public string Estado { get; set; }
        public int Idusuario { get; set; }
        public int Idreporte { get; set; }

        public Reporte IdreporteNavigation { get; set; }
        public Usuario IdusuarioNavigation { get; set; }
    }
}
