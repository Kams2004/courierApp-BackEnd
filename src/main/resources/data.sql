-- Données initiales pour la base de données
-- Départements
INSERT INTO departments (name, code, description) VALUES 
('Ressources Humaines', 'RH', 'Gestion du personnel et recrutement'),
('Commercial', 'COM', 'Ventes et relations clients'),
('Direction', 'DIR', 'Direction générale'),
('Comptabilité', 'COMPTA', 'Gestion financière et comptable'),
('Informatique', 'IT', 'Support technique et développement')
ON CONFLICT (name) DO NOTHING;

-- Utilisateur admin par défaut (mot de passe: admin123)
INSERT INTO users (first_name, last_name, email, password, role, fonction, department_id, created_at) VALUES 
('Admin', 'System', 'admin@entreprise.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iYqiSfFGdMwjcoE1.Nk8Oj2.Ey2S', 'ADMIN', 'Administrateur système', 1, NOW())
ON CONFLICT (email) DO NOTHING;