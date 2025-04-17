package org.viktor_company.library_managment.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.viktor_company.library_managment.domain.lib_branches.LibBranch
import org.viktor_company.library_managment.domain.lib_branches.LibBranchID

interface LibBranchRepo : JpaRepository<LibBranch, LibBranchID>{
}