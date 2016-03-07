namespace JsonCreator
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.CreateNewWord = new System.Windows.Forms.Button();
            this.WordTextBox = new System.Windows.Forms.RichTextBox();
            this.MeaningTextBox = new System.Windows.Forms.RichTextBox();
            this.LanguageTextBox = new System.Windows.Forms.RichTextBox();
            this.AddedDateTextBox = new System.Windows.Forms.RichTextBox();
            this.ActivationDateTextBox = new System.Windows.Forms.RichTextBox();
            this.UsedCountTextBox = new System.Windows.Forms.RichTextBox();
            this.ActiveTextBox = new System.Windows.Forms.RichTextBox();
            this.IndexTextBox = new System.Windows.Forms.RichTextBox();
            this.SaveWord = new System.Windows.Forms.Button();
            this.IndexLabel = new System.Windows.Forms.RichTextBox();
            this.ActiveLabel = new System.Windows.Forms.RichTextBox();
            this.UsedCountLabel = new System.Windows.Forms.RichTextBox();
            this.ActivationDateLabel = new System.Windows.Forms.RichTextBox();
            this.AddedDateLabel = new System.Windows.Forms.RichTextBox();
            this.LanguageLabel = new System.Windows.Forms.RichTextBox();
            this.MeaningLabel = new System.Windows.Forms.RichTextBox();
            this.WordLabel = new System.Windows.Forms.RichTextBox();
            this.LoadJsonFile = new System.Windows.Forms.Button();
            this.NextWord = new System.Windows.Forms.Button();
            this.PreviousWord = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // CreateNewWord
            // 
            this.CreateNewWord.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.CreateNewWord.Location = new System.Drawing.Point(424, 56);
            this.CreateNewWord.Name = "CreateNewWord";
            this.CreateNewWord.Size = new System.Drawing.Size(146, 43);
            this.CreateNewWord.TabIndex = 2;
            this.CreateNewWord.Text = "Create New Word";
            this.CreateNewWord.UseVisualStyleBackColor = false;
            this.CreateNewWord.Click += new System.EventHandler(this.CreateNewWord_Click);
            // 
            // WordTextBox
            // 
            this.WordTextBox.Location = new System.Drawing.Point(196, 128);
            this.WordTextBox.Name = "WordTextBox";
            this.WordTextBox.Size = new System.Drawing.Size(542, 57);
            this.WordTextBox.TabIndex = 4;
            this.WordTextBox.Text = "";
            this.WordTextBox.TextChanged += new System.EventHandler(this.WordTextBox_TextChanged);
            // 
            // MeaningTextBox
            // 
            this.MeaningTextBox.Location = new System.Drawing.Point(196, 191);
            this.MeaningTextBox.Name = "MeaningTextBox";
            this.MeaningTextBox.Size = new System.Drawing.Size(542, 57);
            this.MeaningTextBox.TabIndex = 5;
            this.MeaningTextBox.Text = "";
            this.MeaningTextBox.TextChanged += new System.EventHandler(this.MeaningTextBox_TextChanged);
            // 
            // LanguageTextBox
            // 
            this.LanguageTextBox.Location = new System.Drawing.Point(196, 254);
            this.LanguageTextBox.Name = "LanguageTextBox";
            this.LanguageTextBox.Size = new System.Drawing.Size(542, 57);
            this.LanguageTextBox.TabIndex = 6;
            this.LanguageTextBox.Text = "";
            // 
            // AddedDateTextBox
            // 
            this.AddedDateTextBox.Location = new System.Drawing.Point(196, 317);
            this.AddedDateTextBox.Name = "AddedDateTextBox";
            this.AddedDateTextBox.Size = new System.Drawing.Size(542, 57);
            this.AddedDateTextBox.TabIndex = 7;
            this.AddedDateTextBox.Text = "";
            // 
            // ActivationDateTextBox
            // 
            this.ActivationDateTextBox.Location = new System.Drawing.Point(196, 380);
            this.ActivationDateTextBox.Name = "ActivationDateTextBox";
            this.ActivationDateTextBox.Size = new System.Drawing.Size(542, 57);
            this.ActivationDateTextBox.TabIndex = 8;
            this.ActivationDateTextBox.Text = "";
            // 
            // UsedCountTextBox
            // 
            this.UsedCountTextBox.Location = new System.Drawing.Point(196, 443);
            this.UsedCountTextBox.Name = "UsedCountTextBox";
            this.UsedCountTextBox.Size = new System.Drawing.Size(542, 57);
            this.UsedCountTextBox.TabIndex = 9;
            this.UsedCountTextBox.Text = "";
            // 
            // ActiveTextBox
            // 
            this.ActiveTextBox.Location = new System.Drawing.Point(196, 506);
            this.ActiveTextBox.Name = "ActiveTextBox";
            this.ActiveTextBox.Size = new System.Drawing.Size(542, 57);
            this.ActiveTextBox.TabIndex = 10;
            this.ActiveTextBox.Text = "";
            // 
            // IndexTextBox
            // 
            this.IndexTextBox.Location = new System.Drawing.Point(196, 569);
            this.IndexTextBox.Name = "IndexTextBox";
            this.IndexTextBox.Size = new System.Drawing.Size(542, 57);
            this.IndexTextBox.TabIndex = 11;
            this.IndexTextBox.Text = "";
            this.IndexTextBox.TextChanged += new System.EventHandler(this.IndexTextBox_TextChanged);
            // 
            // SaveWord
            // 
            this.SaveWord.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.SaveWord.ForeColor = System.Drawing.Color.Black;
            this.SaveWord.Location = new System.Drawing.Point(592, 56);
            this.SaveWord.Name = "SaveWord";
            this.SaveWord.Size = new System.Drawing.Size(146, 43);
            this.SaveWord.TabIndex = 3;
            this.SaveWord.Text = "Save JSON File";
            this.SaveWord.UseVisualStyleBackColor = false;
            this.SaveWord.Click += new System.EventHandler(this.SaveWord_Click);
            // 
            // IndexLabel
            // 
            this.IndexLabel.Location = new System.Drawing.Point(20, 569);
            this.IndexLabel.Name = "IndexLabel";
            this.IndexLabel.ReadOnly = true;
            this.IndexLabel.Size = new System.Drawing.Size(152, 57);
            this.IndexLabel.TabIndex = 17;
            this.IndexLabel.TabStop = false;
            this.IndexLabel.Text = "Index";
            this.IndexLabel.TextChanged += new System.EventHandler(this.IndexLabel_TextChanged);
            // 
            // ActiveLabel
            // 
            this.ActiveLabel.Location = new System.Drawing.Point(20, 506);
            this.ActiveLabel.Name = "ActiveLabel";
            this.ActiveLabel.ReadOnly = true;
            this.ActiveLabel.Size = new System.Drawing.Size(152, 57);
            this.ActiveLabel.TabIndex = 15;
            this.ActiveLabel.TabStop = false;
            this.ActiveLabel.Text = "Active";
            this.ActiveLabel.TextChanged += new System.EventHandler(this.ActiveLabel_TextChanged);
            // 
            // UsedCountLabel
            // 
            this.UsedCountLabel.Location = new System.Drawing.Point(20, 443);
            this.UsedCountLabel.Name = "UsedCountLabel";
            this.UsedCountLabel.ReadOnly = true;
            this.UsedCountLabel.Size = new System.Drawing.Size(152, 57);
            this.UsedCountLabel.TabIndex = 13;
            this.UsedCountLabel.TabStop = false;
            this.UsedCountLabel.Text = "Used Count";
            this.UsedCountLabel.TextChanged += new System.EventHandler(this.UsedCountLabel_TextChanged);
            // 
            // ActivationDateLabel
            // 
            this.ActivationDateLabel.Location = new System.Drawing.Point(20, 380);
            this.ActivationDateLabel.Name = "ActivationDateLabel";
            this.ActivationDateLabel.ReadOnly = true;
            this.ActivationDateLabel.Size = new System.Drawing.Size(152, 57);
            this.ActivationDateLabel.TabIndex = 11;
            this.ActivationDateLabel.TabStop = false;
            this.ActivationDateLabel.Text = "Activation Date";
            this.ActivationDateLabel.TextChanged += new System.EventHandler(this.ActivationDateLabel_TextChanged);
            // 
            // AddedDateLabel
            // 
            this.AddedDateLabel.Location = new System.Drawing.Point(20, 317);
            this.AddedDateLabel.Name = "AddedDateLabel";
            this.AddedDateLabel.ReadOnly = true;
            this.AddedDateLabel.Size = new System.Drawing.Size(152, 57);
            this.AddedDateLabel.TabIndex = 9;
            this.AddedDateLabel.TabStop = false;
            this.AddedDateLabel.Text = "Added Date";
            this.AddedDateLabel.TextChanged += new System.EventHandler(this.AddedDateLabel_TextChanged);
            // 
            // LanguageLabel
            // 
            this.LanguageLabel.Location = new System.Drawing.Point(20, 254);
            this.LanguageLabel.Name = "LanguageLabel";
            this.LanguageLabel.ReadOnly = true;
            this.LanguageLabel.Size = new System.Drawing.Size(152, 57);
            this.LanguageLabel.TabIndex = 7;
            this.LanguageLabel.TabStop = false;
            this.LanguageLabel.Text = "Language";
            this.LanguageLabel.TextChanged += new System.EventHandler(this.LanguageLabel_TextChanged);
            // 
            // MeaningLabel
            // 
            this.MeaningLabel.Location = new System.Drawing.Point(20, 191);
            this.MeaningLabel.Name = "MeaningLabel";
            this.MeaningLabel.ReadOnly = true;
            this.MeaningLabel.Size = new System.Drawing.Size(152, 57);
            this.MeaningLabel.TabIndex = 5;
            this.MeaningLabel.TabStop = false;
            this.MeaningLabel.Text = "Meaning";
            this.MeaningLabel.TextChanged += new System.EventHandler(this.MeaningLabel_TextChanged);
            // 
            // WordLabel
            // 
            this.WordLabel.Location = new System.Drawing.Point(20, 128);
            this.WordLabel.Name = "WordLabel";
            this.WordLabel.ReadOnly = true;
            this.WordLabel.Size = new System.Drawing.Size(152, 57);
            this.WordLabel.TabIndex = 3;
            this.WordLabel.TabStop = false;
            this.WordLabel.Text = "Word";
            this.WordLabel.TextChanged += new System.EventHandler(this.WordLabel_TextChanged);
            // 
            // LoadJsonFile
            // 
            this.LoadJsonFile.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.LoadJsonFile.ForeColor = System.Drawing.Color.Black;
            this.LoadJsonFile.Location = new System.Drawing.Point(20, 12);
            this.LoadJsonFile.Name = "LoadJsonFile";
            this.LoadJsonFile.Size = new System.Drawing.Size(146, 43);
            this.LoadJsonFile.TabIndex = 1;
            this.LoadJsonFile.Text = "Load JSON File";
            this.LoadJsonFile.UseVisualStyleBackColor = false;
            this.LoadJsonFile.Click += new System.EventHandler(this.LoadJsonFile_Click);
            // 
            // NextWord
            // 
            this.NextWord.Location = new System.Drawing.Point(67, 98);
            this.NextWord.Name = "NextWord";
            this.NextWord.Size = new System.Drawing.Size(41, 24);
            this.NextWord.TabIndex = 20;
            this.NextWord.TabStop = false;
            this.NextWord.Text = "-->";
            this.NextWord.UseVisualStyleBackColor = true;
            this.NextWord.Click += new System.EventHandler(this.NextWord_Click);
            // 
            // PreviousWord
            // 
            this.PreviousWord.Location = new System.Drawing.Point(20, 98);
            this.PreviousWord.Name = "PreviousWord";
            this.PreviousWord.Size = new System.Drawing.Size(41, 24);
            this.PreviousWord.TabIndex = 21;
            this.PreviousWord.TabStop = false;
            this.PreviousWord.Text = "<--";
            this.PreviousWord.UseVisualStyleBackColor = true;
            this.PreviousWord.Click += new System.EventHandler(this.PreviousWord_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(766, 643);
            this.Controls.Add(this.PreviousWord);
            this.Controls.Add(this.NextWord);
            this.Controls.Add(this.LoadJsonFile);
            this.Controls.Add(this.SaveWord);
            this.Controls.Add(this.IndexLabel);
            this.Controls.Add(this.IndexTextBox);
            this.Controls.Add(this.ActiveLabel);
            this.Controls.Add(this.ActiveTextBox);
            this.Controls.Add(this.UsedCountLabel);
            this.Controls.Add(this.UsedCountTextBox);
            this.Controls.Add(this.ActivationDateLabel);
            this.Controls.Add(this.ActivationDateTextBox);
            this.Controls.Add(this.AddedDateLabel);
            this.Controls.Add(this.AddedDateTextBox);
            this.Controls.Add(this.LanguageLabel);
            this.Controls.Add(this.LanguageTextBox);
            this.Controls.Add(this.MeaningLabel);
            this.Controls.Add(this.MeaningTextBox);
            this.Controls.Add(this.WordLabel);
            this.Controls.Add(this.WordTextBox);
            this.Controls.Add(this.CreateNewWord);
            this.Name = "Form1";
            this.Text = "JSON Word Creator v1.0";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button CreateNewWord;
        private System.Windows.Forms.RichTextBox WordTextBox;
        private System.Windows.Forms.RichTextBox MeaningTextBox;
        private System.Windows.Forms.RichTextBox LanguageTextBox;
        private System.Windows.Forms.RichTextBox AddedDateTextBox;
        private System.Windows.Forms.RichTextBox ActivationDateTextBox;
        private System.Windows.Forms.RichTextBox UsedCountTextBox;
        private System.Windows.Forms.RichTextBox ActiveTextBox;
        private System.Windows.Forms.RichTextBox IndexTextBox;
        private System.Windows.Forms.Button SaveWord;
        private System.Windows.Forms.RichTextBox IndexLabel;
        private System.Windows.Forms.RichTextBox ActiveLabel;
        private System.Windows.Forms.RichTextBox UsedCountLabel;
        private System.Windows.Forms.RichTextBox ActivationDateLabel;
        private System.Windows.Forms.RichTextBox AddedDateLabel;
        private System.Windows.Forms.RichTextBox LanguageLabel;
        private System.Windows.Forms.RichTextBox MeaningLabel;
        private System.Windows.Forms.RichTextBox WordLabel;
        private System.Windows.Forms.Button LoadJsonFile;
        private System.Windows.Forms.Button NextWord;
        private System.Windows.Forms.Button PreviousWord;
    }
}

