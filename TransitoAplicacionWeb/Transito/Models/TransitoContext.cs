using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace Transito.Models
{
    public partial class TransitoContext : DbContext
    {
        public TransitoContext()
        {
        }

       

        public virtual DbSet<Aseguradora> Aseguradora { get; set; }
        public virtual DbSet<Cargo> Cargo { get; set; }
        public virtual DbSet<Conductor> Conductor { get; set; }
        public virtual DbSet<ConductorBitacoraAcceso> ConductorBitacoraAcceso { get; set; }
        public virtual DbSet<Dictamen> Dictamen { get; set; }
        public virtual DbSet<Evidencia> Evidencia { get; set; }
        public virtual DbSet<Reporte> Reporte { get; set; }
        public virtual DbSet<Usuario> Usuario { get; set; }
        public virtual DbSet<UsuarioBitacoraAcceso> UsuarioBitacoraAcceso { get; set; }
        public virtual DbSet<Vehiculo> Vehiculo { get; set; }


        public TransitoContext(DbContextOptions<TransitoContext> options)
           : base(options)
        {
        }


        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Aseguradora>(entity =>
            {
                entity.HasKey(e => e.Idaseguradora);

                entity.Property(e => e.Idaseguradora).HasColumnName("IDAseguradora");

                entity.Property(e => e.Nombre)
                    .IsRequired()
                    .HasColumnName("nombre")
                    .HasMaxLength(50)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<Cargo>(entity =>
            {
                entity.HasKey(e => e.Idcargo);

                entity.Property(e => e.Idcargo).HasColumnName("IDCargo");

                entity.Property(e => e.NombreCargo)
                    .HasColumnName("nombreCargo")
                    .HasMaxLength(20)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<Conductor>(entity =>
            {
                entity.HasKey(e => e.Idconductor);

                entity.Property(e => e.Idconductor).HasColumnName("IDConductor");

                entity.Property(e => e.ApellidoMaterno)
                    .IsRequired()
                    .HasColumnName("apellidoMaterno")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.ApellidoPaterno)
                    .IsRequired()
                    .HasColumnName("apellidoPaterno")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.FechaNacimiento)
                    .HasColumnName("fechaNacimiento")
                    .HasColumnType("date");

                entity.Property(e => e.Nombre)
                    .IsRequired()
                    .HasColumnName("nombre")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.NumeroLicencia)
                    .IsRequired()
                    .HasColumnName("numeroLicencia")
                    .HasMaxLength(10)
                    .IsUnicode(false);

                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasColumnName("password")
                    .HasMaxLength(20)
                    .IsUnicode(false);

                entity.Property(e => e.Telefono)
                    .IsRequired()
                    .HasColumnName("telefono")
                    .HasMaxLength(10)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<ConductorBitacoraAcceso>(entity =>
            {
                entity.ToTable("Conductor_BitacoraAcceso");

                entity.Property(e => e.Id).HasColumnName("ID");

                entity.Property(e => e.Fecha).HasColumnType("datetime");

                entity.Property(e => e.Idconductor).HasColumnName("IDConductor");

                entity.Property(e => e.TokenAcceso)
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.HasOne(d => d.IdconductorNavigation)
                    .WithMany(p => p.ConductorBitacoraAcceso)
                    .HasForeignKey(d => d.Idconductor)
                    .HasConstraintName("FK_Conductor_BitacoraAcceso_Conductor");
            });

            modelBuilder.Entity<Dictamen>(entity =>
            {
                entity.HasKey(e => e.Folio);

                entity.Property(e => e.Folio).ValueGeneratedNever();

                entity.Property(e => e.Descripcion)
                    .IsRequired()
                    .HasMaxLength(300)
                    .IsUnicode(false);

                entity.Property(e => e.Estado)
                    .IsRequired()
                    .HasColumnName("estado")
                    .HasMaxLength(15)
                    .IsUnicode(false);

                entity.Property(e => e.FechaDictamen)
                    .HasColumnName("fechaDictamen")
                    .HasColumnType("date");

                entity.Property(e => e.Idusuario).HasColumnName("IDUsuario");

                entity.HasOne(d => d.IdusuarioNavigation)
                    .WithMany(p => p.Dictamen)
                    .HasForeignKey(d => d.Idusuario)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Dictamen_Usuario");
            });

            modelBuilder.Entity<Evidencia>(entity =>
            {
                entity.HasKey(e => e.Idevidencia);

                entity.Property(e => e.Idevidencia).HasColumnName("IDEvidencia");

                entity.Property(e => e.FotoDerecha1).HasColumnName("fotoDerecha1");

                entity.Property(e => e.FotoDerecha2).HasColumnName("fotoDerecha2");

                entity.Property(e => e.FotoFrontal1).HasColumnName("fotoFrontal1");

                entity.Property(e => e.FotoFrontal2).HasColumnName("fotoFrontal2");

                entity.Property(e => e.FotoIzquierda1).HasColumnName("fotoIzquierda1");

                entity.Property(e => e.FotoIzquierda2).HasColumnName("fotoIzquierda2");

                entity.Property(e => e.FotoTrasera1).HasColumnName("fotoTrasera1");

                entity.Property(e => e.FotoTrasera2).HasColumnName("fotoTrasera2");
            });

            modelBuilder.Entity<Reporte>(entity =>
            {
                entity.HasKey(e => e.Idreporte);

                entity.Property(e => e.Idreporte)
                    .HasColumnName("IDReporte")
                    .ValueGeneratedNever();

                entity.Property(e => e.AseguradoraImplicado).HasColumnName("aseguradoraImplicado");

                entity.Property(e => e.ColorImplicado)
                    .IsRequired()
                    .HasColumnName("colorImplicado")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.DictamenFolio).HasColumnName("dictamen_folio");

                entity.Property(e => e.FechaSuceso)
                    .HasColumnName("fechaSuceso")
                    .HasColumnType("date");

                entity.Property(e => e.Fotos)
                    .IsRequired()
                    .HasColumnName("fotos");

                entity.Property(e => e.Idevidencia).HasColumnName("IDEvidencia");

                entity.Property(e => e.Latitud).HasColumnName("latitud");

                entity.Property(e => e.Longitud).HasColumnName("longitud");

                entity.Property(e => e.MarcaImplicado)
                    .IsRequired()
                    .HasColumnName("marcaImplicado")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.ModeloImplicado)
                    .IsRequired()
                    .HasColumnName("modeloImplicado")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.NombreImplicado)
                    .IsRequired()
                    .HasColumnName("nombreImplicado")
                    .HasMaxLength(250)
                    .IsUnicode(false);

                entity.Property(e => e.NumeroPolizaImplicado)
                    .HasColumnName("numeroPolizaImplicado")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.PlacaImplicado)
                    .IsRequired()
                    .HasColumnName("placaImplicado")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.HasOne(d => d.AseguradoraImplicadoNavigation)
                    .WithMany(p => p.Reporte)
                    .HasForeignKey(d => d.AseguradoraImplicado)
                    .HasConstraintName("FK_Reporte_Aguradora");

                entity.HasOne(d => d.DictamenFolioNavigation)
                    .WithMany(p => p.Reporte)
                    .HasForeignKey(d => d.DictamenFolio)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Reporte_Dictamen");

                entity.HasOne(d => d.IdevidenciaNavigation)
                    .WithMany(p => p.Reporte)
                    .HasForeignKey(d => d.Idevidencia)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Reporte_Evidencia");
            });

            modelBuilder.Entity<Usuario>(entity =>
            {
                entity.HasKey(e => e.Idusuario);

                entity.Property(e => e.Idusuario).HasColumnName("IDUsuario");

                entity.Property(e => e.ApellidoMaterno)
                    .IsRequired()
                    .HasColumnName("apellidoMaterno")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.ApellidoPaterno)
                    .IsRequired()
                    .HasColumnName("apellidoPaterno")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.Idcargo).HasColumnName("IDCargo");

                entity.Property(e => e.Nombre)
                    .IsRequired()
                    .HasColumnName("nombre")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasColumnName("password")
                    .HasMaxLength(20)
                    .IsUnicode(false);

                entity.Property(e => e.Username)
                    .IsRequired()
                    .HasColumnName("username")
                    .HasMaxLength(20)
                    .IsUnicode(false);

                entity.HasOne(d => d.IdcargoNavigation)
                    .WithMany(p => p.Usuario)
                    .HasForeignKey(d => d.Idcargo)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Usuario_Cargo");
            });

            modelBuilder.Entity<UsuarioBitacoraAcceso>(entity =>
            {
                entity.ToTable("Usuario_BitacoraAcceso");

                entity.Property(e => e.Id)
                    .HasColumnName("ID")
                    .ValueGeneratedNever();

                entity.Property(e => e.Fecha).HasColumnType("datetime");

                entity.Property(e => e.Idusuario).HasColumnName("IDUsuario");

                entity.Property(e => e.TokenAcceso)
                    .HasMaxLength(30)
                    .IsUnicode(false);

                entity.HasOne(d => d.IdusuarioNavigation)
                    .WithMany(p => p.UsuarioBitacoraAcceso)
                    .HasForeignKey(d => d.Idusuario)
                    .HasConstraintName("FK_Usuario_BitacoraAcceso_Usuario");
            });

            modelBuilder.Entity<Vehiculo>(entity =>
            {
                entity.HasKey(e => e.Placas);

                entity.Property(e => e.Placas)
                    .HasColumnName("placas")
                    .HasMaxLength(9)
                    .IsUnicode(false)
                    .ValueGeneratedNever();

                entity.Property(e => e.Anio).HasColumnName("anio");

                entity.Property(e => e.Color)
                    .IsRequired()
                    .HasColumnName("color")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.Idaseguradora).HasColumnName("IDAseguradora");

                entity.Property(e => e.Idconductor).HasColumnName("IDConductor");

                entity.Property(e => e.Marca)
                    .IsRequired()
                    .HasColumnName("marca")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.Modelo)
                    .IsRequired()
                    .HasColumnName("modelo")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.PolizaSeguro)
                    .HasColumnName("polizaSeguro")
                    .HasMaxLength(20)
                    .IsUnicode(false);

                entity.HasOne(d => d.IdaseguradoraNavigation)
                    .WithMany(p => p.Vehiculo)
                    .HasForeignKey(d => d.Idaseguradora)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Vehiculo_Aseguradora");

                entity.HasOne(d => d.IdconductorNavigation)
                    .WithMany(p => p.Vehiculo)
                    .HasForeignKey(d => d.Idconductor)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Vehiculo_Conductor");
            });
        }
    }
}
