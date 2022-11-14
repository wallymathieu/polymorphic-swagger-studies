using System.Collections.Generic;

namespace SomeBasicFileStoreApp.Core
{
    public record Customer(int Id, Names Name, int Version)
    {
        public static Customer Create(int id, string firstName, string lastName, int version)
        {
            return new Customer(
            Id: id,
            Name: new Names(firstName,lastName),
            Version: version);
        }
    }

    public class Names
    {
        public Names(string first, string last)
        {
            First = first;
            Last = last;
        }

        /// <summary>
        /// First name
        /// </summary>
        public string First { get; }
        /// <summary>
        /// Last name
        /// </summary>
        public string Last { get; }
    }
}
