using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using FilmClub.CSharp.ASP.NETCore.Movies.Database;

namespace FilmClub.CSharp.ASP.NETCore.Models
{
    public class MovieContext : DbContext
    {
        public MovieContext (DbContextOptions<MovieContext> options)
            : base(options)
        {
        }

        public DbSet<FilmClub.CSharp.ASP.NETCore.Movies.Database.Movie> Movie { get; set; }
    }
}
