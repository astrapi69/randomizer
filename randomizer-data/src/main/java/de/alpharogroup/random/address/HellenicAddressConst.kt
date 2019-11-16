/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.random.address

/**
 * The class [HellenicAddressConst] is a constant class for hellenic address data like first
 * and last names and streets.
 */
class HellenicAddressConst {
    companion object {

        /** Constant array with hellenic mail firstname.  */
        var MAIL_FIRSTNAME = arrayOf("Αστέριος", "Κώστας", "Γιάννης", "Νίκος", "Βασίλης", "Αλέξανδρος", "Γιώργιος", "Μηχάλης", "Δημήτρης", "Αντώνης")

        /** Constant array with hellenic femail firstname.  */
        var FEMAIL_FIRSTNAME = arrayOf("Αναστασία", "Βασηλική", "Μαρία", "Σοφία", "Ελένη", "Δάφνη", "Ολυμπία", "Κασσάνδρα", "Αρτεμης", "Αντιγώνη", "Αλεχάνδρα", "Θαλία", "Νίκη", "Αγγελική", "Στυλιανή", "Δήμητρα", "Χρηστίνα", "Ελεφθερία", "Ιωάννα", "Όλγα", "Σταυρούλα", "¶ννα", "Αθανασία", "Αθηνά", "Ευτυχία", "Ευαγγελία", "Παρασκευή")

        /** Constant array with hellenic mail lastname.  */
        var MAIL_LASTNAME = arrayOf("Ράπτης", "Θεοδωρακάκις", "Κουμλέλης", "Σαββίδης", "Αντωνιάδης", "Αλεξίδης", "Μακρίδης", "Δασόπουλος", "Ζουρουφίδης", "Ζαπουνίδης", "Καπουράνης", "Λαλίκος", "Μπίλας")

        /** Constant array with hellenic femail lastname.  */
        var FEMAIL_LASTNAME = arrayOf("Ράπτη", "Θεοδωρακάκι", "Κουμλέλη", "Σαββίδη", "Αντωνιάδη", "Αλεξίδη", "Μακρίδη", "Δασοπούλου", "Ζουρουφίδη", "Ζαπουνίδη", "Καπουράνη", "Λαλίκο", "Μπίλα")

        /** Constant array with hellenic street names.  */
        var STREETS = arrayOf("Γαστούνης", "Βασιλικά Βουτών", "Μητροπόλεως", "Λεωφόρος Συγγρού", "Καπλάνη", "Ριζοσπαστών Βουλευτών", "Εθνομαρτύρων", "Νίκης", "Παρασίου", "Σόλωνος", "Βουλής", "Aμαρουσίου Χαλανδρίου")

        /** Constant array with hellenic city names and zip code.  */
        var CITIES = arrayOf(arrayOf("49100", "Κέρκυρα"), arrayOf("71500", "Ηράκλειο"), arrayOf("10185", "Αθήνα"), arrayOf("11527", "Αθήνα"), arrayOf("19002", "Παιανία"), arrayOf("10552", "Αθήνα"))

        /** Constant array with hellenic company names.  */
        var COMPANYNAMES = arrayOf("ΠΛΗΡΟΦΟΡΙΚΗ ΕΚΡΑΓΗ", "5 Π", "ΣΥΣΤΗΜΑΤΑ ΕΠΕ", "ΤΕΛΕΙΑ ΕΚΠΑΙΔΕΥΣΗ", "ΣΕΜΙΝΑΡΙΑ ΕΠΕ", "ΦΡΟΝΤΗΣΤΙΡΙΟ ΠΡΟΤΥΠΟ", "ΦΡΟΝΤΗΣΤΙΡΙΟ ΕΡΜΗΣ", "ΦΡΟΝΤΗΣΤΙΡΙΟ ΑΛΚΙΒΙΑΔΗΣ", "ΦΡΟΝΤΗΣΤΙΡΙΟ ΠΛΑΤΟΝΑΣ", "ΦΡΟΝΤΗΣΤΙΡΙΟ ΑΠΙΣΤΟΤΕΛΗΣ", "ΦΡΟΝΤΗΣΤΙΡΙΟ ΣΩΚΡΑΤΗΣ")
    }

}