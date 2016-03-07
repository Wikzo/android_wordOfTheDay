using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Security;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Newtonsoft.Json;

namespace JsonCreator
{
    public partial class Form1 : Form
    {
        private Stream _myJsonStream = null;


        private List<Word> _words;
        private Word _currentWord;
        private int _index = 0;

        public Form1()
        {
            InitializeComponent();

            _words = new List<Word>();
            _currentWord = new Word(_index);
            SetTextboxesToCurrentWord();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void CreateNewWord_Click(object sender, EventArgs e)
        {
            if (_currentWord != null)
                SaveCurrentWord();

            _currentWord = new Word(_index);
            SetTextboxesToCurrentWord();
        }

        private void SaveCurrentWord()
        {
            if (!_words.Contains(_currentWord))
            {
                _words.Add(_currentWord);
                _index = _words.Count();
            }

            SaveTextboxDataToCurrentWord();

            //SaveJson();

        }

        private void SetTextboxesToCurrentWord()
        {
            if (_currentWord == null)
                return;

            this.WordTextBox.Text = _currentWord.word;
            this.MeaningTextBox.Text = _currentWord.meaning;
            this.LanguageTextBox.Text = _currentWord.language;
            this.AddedDateTextBox.Text = _currentWord.addedDate;
            this.ActivationDateTextBox.Text = _currentWord.activationDate;
            this.UsedCountTextBox.Text = _currentWord.usedCount.ToString();
            this.ActiveTextBox.Text = _currentWord.active.ToString();
            this.IndexTextBox.Text = _currentWord.index.ToString();
        }


        private void SaveWord_Click(object sender, EventArgs e)
        {
            if (_currentWord != null)
                SaveCurrentWord();

            string results = SaveJson();
            Console.WriteLine(results);
        }

        private void SaveTextboxDataToCurrentWord()
        {
            if (_currentWord == null)
                return;

            _currentWord.word = this.WordTextBox.Text;
            _currentWord.meaning = this.MeaningTextBox.Text;
            _currentWord.language = this.LanguageTextBox.Text;
            _currentWord.addedDate = this.AddedDateTextBox.Text;
            _currentWord.activationDate = this.ActivationDateTextBox.Text;
            _currentWord.usedCount = Convert.ToInt32(this.UsedCountTextBox.Text);
            _currentWord.active = Convert.ToBoolean(this.ActiveTextBox.Text);
            _currentWord.index = Convert.ToInt32(this.IndexTextBox.Text);
        }

        private string SaveJson()
        {
            string content = JsonConvert.SerializeObject(_words, Formatting.Indented);
            SaveJsonToFile(content);
            return content;
        }

        private void SaveJsonToFile(string content)
        {
            // https://stackoverflow.com/questions/5067662/winforms-save-as#5067676

            Stream myStream = null;

            using (SaveFileDialog dialog = new SaveFileDialog())
            {
                dialog.Filter = "JSON Files|*.JSON|All files (*.*)|*.*";
                dialog.FilterIndex = 2;
                dialog.RestoreDirectory = true;

                if (dialog.ShowDialog() == DialogResult.OK)
                {
                    // Can use dialog.FileName
                    if ((myStream = dialog.OpenFile()) != null)
                    {
                        System.IO.StreamWriter file = new System.IO.StreamWriter(myStream);
                        file.WriteLine(content);
                        file.Close();
                        myStream.Close();
                    }

                }
            }
        }

        private List<Word> LoadJson(string path)
        {
            return JsonConvert.DeserializeObject<List<Word>>(path);
        }

        private void LoadJsonFile_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog1 = new OpenFileDialog();

            //openFileDialog1.InitialDirectory = "c:\\";
            //openFileDialog1.Filter = "JSON files (*.json)";
            openFileDialog1.Filter = "JSON Files|*.JSON|All files (*.*)|*.*";
            openFileDialog1.FilterIndex = 2;
            openFileDialog1.RestoreDirectory = true;

            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    if ((_myJsonStream = openFileDialog1.OpenFile()) != null)
                    {
                        using (_myJsonStream)
                        {
                            string loadedData = ReadJsonData(_myJsonStream);

                            if (loadedData != "")
                                InitializeWordsFromJson(loadedData);
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error: Could not read file from disk. Original error: " + ex.Message);
                }
            }
        }

        private string ReadJsonData(Stream stream)
        {
            string lines = "";
            try
            {
                using (StreamReader sr = new StreamReader(stream))
                {
                    lines = sr.ReadToEnd();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("The file could not be read:");
                Console.WriteLine(e.Message);
            }

            return lines;
        }

        private void InitializeWordsFromJson(string data)
        {
            List<Word> temp = LoadJson(data);

            _currentWord = null;
            _words.Clear();

            foreach (Word w in temp)
                _words.Add(w);

            _index = 0;
            _currentWord = _words[_index];
            SetTextboxesToCurrentWord();

        }

        private void NextWord_Click(object sender, EventArgs e)
        {
            if (_words.Count < 1)
                return;

            _index = (_index + 1 < _words.Count) ? _index + 1 : 0;

            ChangeWord();


        }

        private void PreviousWord_Click(object sender, EventArgs e)
        {
            if (_words.Count < 1)
                return;

            _index = (_index - 1 > 1) ? _index - 1 : _words.Count-1;

            ChangeWord();


        }

        private void ChangeWord()
        {
            if (_words.Count < 1)
                return;

            SaveTextboxDataToCurrentWord();
            _currentWord = _words[_index];
            SetTextboxesToCurrentWord();
        }

        private void WordTextBox_TextChanged(object sender, EventArgs e)
        {

        }

        private void WordLabel_TextChanged(object sender, EventArgs e)
        {

        }

        private void LanguageLabel_TextChanged(object sender, EventArgs e)
        {

        }

        private void IndexTextBox_TextChanged(object sender, EventArgs e)
        {

        }

        private void AddedDateLabel_TextChanged(object sender, EventArgs e)
        {

        }

        private void ActiveLabel_TextChanged(object sender, EventArgs e)
        {

        }

        private void IndexLabel_TextChanged(object sender, EventArgs e)
        {

        }

        private void MeaningLabel_TextChanged(object sender, EventArgs e)
        {

        }

        private void ActivationDateLabel_TextChanged(object sender, EventArgs e)
        {

        }

        private void UsedCountLabel_TextChanged(object sender, EventArgs e)
        {

        }

        private void MeaningTextBox_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
